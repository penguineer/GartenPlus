package com.penguineering.gartenplus.auth;

import com.penguineering.gartenplus.auth.mapping.OidcMapping;
import com.penguineering.gartenplus.auth.mapping.OidcMappingKey;
import com.penguineering.gartenplus.auth.mapping.OidcMappingRepository;
import com.penguineering.gartenplus.auth.user.UserDTO;
import com.penguineering.gartenplus.auth.user.UserEntity;
import com.penguineering.gartenplus.auth.user.UserRepository;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public class GithubOidcUserService extends DefaultOAuth2UserService {
    private final OidcMappingRepository oidcMappingRepository;
    private final UserRepository userRepository;

    public GithubOidcUserService(
            OidcMappingRepository oidcMappingRepository,
            UserRepository userRepository) {
        this.oidcMappingRepository = oidcMappingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User origin = super.loadUser(userRequest);

        try {
            // Custom logic to map GitHub user info to your application's user model
            return findUserByOriginId(origin)
                    .map(user -> new GartenplusUser(origin, user))
                    .orElseGet(() -> new GartenplusUser(origin, createUser(origin)));
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

    private Optional<UserDTO> findUserByOriginId(OAuth2User origin) {
        return Optional
                .ofNullable(origin)
                .map(this::toMappingKey)
                .flatMap(oidcMappingRepository::findById)
                .map(OidcMapping::getUserId)
                .flatMap(userRepository::findById)
                .map(UserEntity::toDTO);
    }

    private UserDTO createUser(OAuth2User origin) {
        final String origin_id = Optional.ofNullable(origin.getAttribute("id"))
                .map(Object::toString)
                .orElseThrow(() -> new IllegalArgumentException("id attribute is missing"));
        final String origin_name = origin.getAttribute("name");
        final String origin_email = origin.getAttribute("email");
        final String origin_avatar_url = origin.getAttribute("avatar_url");

        UserEntity userEntity = new UserEntity();
        userEntity.setDisplayName(origin_name);
        userEntity.setEmail(origin_email);
        Optional.ofNullable(origin_avatar_url)
                .map(URI::create)
                .ifPresent(userEntity::setAvatarUrl);
        UserEntity savedUserEntity = userRepository.save(userEntity);

        OidcMapping oidcMapping = new OidcMapping();
        oidcMapping.setIssuer("github");
        oidcMapping.setOidcId(origin_id);
        oidcMapping.setUserId(savedUserEntity.getId());
        oidcMappingRepository.save(oidcMapping);

        return savedUserEntity.toDTO();
    }
}