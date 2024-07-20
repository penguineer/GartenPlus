// In AdminRoleInitializer.java

package com.penguineering.gartenplus;

import com.penguineering.gartenplus.auth.role.SystemRole;
import com.penguineering.gartenplus.auth.role.SystemRoleService;
import com.penguineering.gartenplus.auth.user.UserEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "gartenplus.security.auto-admin", havingValue = "true")
public class AutoAdministratorInitializer {
    private static final Logger logger = LoggerFactory.getLogger(AutoAdministratorInitializer.class);

    private final UserEntityService userEntityService;
    private final SystemRoleService systemRoleService;

    public AutoAdministratorInitializer(UserEntityService userEntityService, SystemRoleService systemRoleService) {
        this.userEntityService = userEntityService;
        this.systemRoleService = systemRoleService;
    }

    @Bean
    public CommandLineRunner checkAndGrantAdminRole() {
        return args -> {
            boolean hasAdmin = userEntityService.getAllUsers().stream()
                    .anyMatch(user -> systemRoleService.getRolesForUser(user.id()).contains(SystemRole.ADMINISTRATOR));

            if (!hasAdmin) {
                logger.warn("AUTO-ADMIN: No administrator found, granting all users the administrator role");

                userEntityService.getAllUsers().forEach(user -> 
                    systemRoleService.setRoleForUser(SystemRole.ADMINISTRATOR, user.id())
                );
            } else
                logger.info("AUTO-ADMIN: Administrator found, skipping role assignment");
        };
    }
}
