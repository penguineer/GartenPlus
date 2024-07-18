package com.penguineering.gartenplus.auth;

import com.penguineering.gartenplus.auth.user.UserDTO;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;
import java.util.function.Supplier;

@Configuration
public class CurrentUserSupplierFactory {
    @Bean(name = "user")
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Supplier<UserDTO> getCurrentUserSupplier() {
        return () -> retrieveUserFromSession().orElse(null);
    }

    private Optional<UserDTO> retrieveUserFromSession() {
        return retrieveSecurityContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(GartenplusUser.class::cast)
                .map(GartenplusUser::getUser);
    }

    private Optional<SecurityContext> retrieveSecurityContext() {
        return Optional.ofNullable(VaadinSession.getCurrent())
                .map(VaadinSession::getSession)
                .map(s -> s.getAttribute("SPRING_SECURITY_CONTEXT"))
                .map(SecurityContext.class::cast);
    }
}
