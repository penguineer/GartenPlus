package com.penguineering.gartenplus.auth.role;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.server.WebSession;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class RoleMappingKey implements Serializable {
    private String roleHandle;
    private UUID userId;

    public static RoleMappingKey of(String roleHandle, UUID userId) {
        return new RoleMappingKey(roleHandle, userId);
    }

    public RoleMappingKey(String roleHandle, UUID userId) {
        this.roleHandle = roleHandle;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return roleHandle + "/" + userId;
    }
}
