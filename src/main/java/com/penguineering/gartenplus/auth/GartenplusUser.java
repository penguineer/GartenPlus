package com.penguineering.gartenplus.auth;

import com.penguineering.gartenplus.auth.role.SystemRole;
import com.penguineering.gartenplus.auth.user.UserDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GartenplusUser implements OAuth2User {
    private final OAuth2User origin;

    @Getter
    private final UserDTO user;

    @Getter
    private final Set<SystemRole> roles;

    public GartenplusUser(OAuth2User origin, UserDTO user, Set<SystemRole> roles) {
        this.origin = origin;
        this.user = user;
        this.roles = roles;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return origin.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SystemRole::asSpringRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public String getName() {
        return user.displayName();
    }
}
