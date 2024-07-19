package com.penguineering.gartenplus.auth;

import com.penguineering.gartenplus.ui.content.LoginPage;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends VaadinWebSecurity {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        setLoginView(http, LoginPage.class);

        http.oauth2Login(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/"));

        http.sessionManagement(session -> session
                .sessionFixation().migrateSession());

        http.logout(logout -> logout
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"));

        // Allow unauthenticated access to Health Endpoint and logo
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(AntPathRequestMatcher.antMatcher("/login")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/assets/github-mark-white.svg")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/actuator/health")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/assets/GartenPlusLogo.png")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/assets/GartenPlusLogoRounded.png")).permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/assets/ImpressumAddress.png")).permitAll()
        );

        super.configure(http);
    }

}
