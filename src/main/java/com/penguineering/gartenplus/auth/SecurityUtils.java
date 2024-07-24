package com.penguineering.gartenplus.auth;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityUtils {

    public static Set<String> getCurrentUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Set.of(); // Return an empty set if the user is not authenticated
        }
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(r -> r.substring("ROLE_".length())) // Reduce to the role name
                .collect(Collectors.toSet());
    }

    public static boolean isUserAuthorizedForComponent(Class<? extends Component> componentClass, Set<String> userRoles) {
        if (Objects.nonNull(componentClass.getAnnotation(AnonymousAllowed.class)))
            return true; // Accessible to all users, authenticated or not


        if (Objects.nonNull(componentClass.getAnnotation(PermitAll.class)))
            return true; // Accessible to all authenticated users


        RolesAllowed rolesAllowed = componentClass.getAnnotation(RolesAllowed.class);
        return Optional.ofNullable(rolesAllowed)
                .map(RolesAllowed::value)
                .map(Arrays::stream)
                .flatMap(stream -> stream.filter(userRoles::contains).findFirst())
                .isPresent();
    }
}