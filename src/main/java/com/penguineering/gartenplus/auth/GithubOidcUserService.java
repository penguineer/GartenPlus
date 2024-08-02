package com.penguineering.gartenplus.auth;

import com.penguineering.gartenplus.auth.mapping.OidcMapping;
import com.penguineering.gartenplus.auth.mapping.OidcMappingKey;
import com.penguineering.gartenplus.auth.mapping.OidcMappingRepository;
import com.penguineering.gartenplus.auth.role.SystemRoleService;
import com.penguineering.gartenplus.auth.user.UserDTO;
import com.penguineering.gartenplus.auth.user.UserEntity;
import com.penguineering.gartenplus.auth.user.UserEntityService;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

@Service
public class GithubOidcUserService extends DefaultOAuth2UserService {
    private final OidcMappingRepository oidcMappingRepository;
    private final UserEntityService userEntityService;
    private final SystemRoleService systemRoleService;

    public GithubOidcUserService(
            OidcMappingRepository oidcMappingRepository,
            UserEntityService userEntityService,
            SystemRoleService systemRoleService) {
        this.oidcMappingRepository = oidcMappingRepository;
        this.userEntityService = userEntityService;
        this.systemRoleService = systemRoleService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User origin = super.loadUser(userRequest);

        try {
            // Custom logic to map GitHub user info to your application's user model
            return findUserByOriginId(origin)
                    .map(user -> updateUserFromOIDC(origin, user))
                    .map(UserEntity::toDTO)
                    .flatMap(user -> Optional.of(user.id())
                            .map(systemRoleService::getRolesForUser)
                            .map(roles -> new GartenplusUser(origin, user, roles)))
                    .orElseGet(() -> new GartenplusUser(origin, createUser(origin), Set.of()));
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException("Problem during login", e);
        }
    }

    private OidcMappingKey toMappingKey(OAuth2User origin) {
        return Optional.ofNullable(origin.getAttribute("id"))
                .map(String::valueOf)
                .map(id -> OidcMappingKey.of("github", id))
                .orElseThrow(() -> new IllegalArgumentException("id attribute is missing"));
    }

    private Optional<UserEntity> findUserByOriginId(OAuth2User origin) {
        return Optional
                .ofNullable(origin)
                .map(this::toMappingKey)
                .flatMap(oidcMappingRepository::findById)
                .map(OidcMapping::getUserId)
                .flatMap(userEntityService::getUser);
    }

    private UserDTO createUser(OAuth2User origin) {
        final String origin_id = Optional.ofNullable(origin.getAttribute("id"))
                .map(Object::toString)
                .orElseThrow(() -> new IllegalArgumentException("id attribute is missing"));
        final String origin_name = determineDisplayName(origin);
        final String origin_email = origin.getAttribute("email");
        final String origin_avatar_url = origin.getAttribute("avatar_url");

        UserEntity userEntity = new UserEntity();
        userEntity.setDisplayName(origin_name);
        userEntity.setEmail(origin_email);
        Optional.ofNullable(origin_avatar_url)
                .map(URI::create)
                .ifPresent(userEntity::setAvatarUrl);
        UserEntity savedUserEntity = userEntityService.save(userEntity);

        OidcMapping oidcMapping = new OidcMapping();
        oidcMapping.setIssuer("github");
        oidcMapping.setOidcId(origin_id);
        oidcMapping.setUserId(savedUserEntity.getId());
        oidcMappingRepository.save(oidcMapping);

        return savedUserEntity.toDTO();
    }

    private UserEntity updateUserFromOIDC(OAuth2User origin, UserEntity user) {
        final String origin_name = determineDisplayName(origin);
        final String origin_email = origin.getAttribute("email");
        final String origin_avatar_url = origin.getAttribute("avatar_url");

        user.setDisplayName(origin_name);
        user.setEmail(origin_email);
        user.setAvatarUrl(
                Optional.ofNullable(origin_avatar_url)
                        .map(URI::create)
                        .orElse(null));

        return userEntityService.save(user);
    }

    private String determineDisplayName(OAuth2User origin) {
        return Optional.ofNullable(origin.getAttribute("name"))
                .or(() -> Optional.ofNullable(origin.getAttribute("login")))
                .map(String::valueOf)
                .orElseThrow(() -> new IllegalArgumentException("Name and login attribute are missing"));
    }
}
