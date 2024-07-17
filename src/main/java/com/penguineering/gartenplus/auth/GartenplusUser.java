package com.penguineering.gartenplus.auth;

import com.penguineering.gartenplus.auth.user.UserDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class GartenplusUser implements OAuth2User {
    private final OAuth2User origin;

    @Getter
    private final UserDTO user;

    public GartenplusUser(OAuth2User origin, UserDTO user) {
        this.origin = origin;
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return origin.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return origin.getAuthorities();
    }

    @Override
    public String getName() {
        return user.displayName();
    }
}
