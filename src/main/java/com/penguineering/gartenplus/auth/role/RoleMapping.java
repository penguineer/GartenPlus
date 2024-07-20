package com.penguineering.gartenplus.auth.role;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"roleHandle", "userId"})
@IdClass(RoleMappingKey.class)
@Table(name = "role_mapping")
public class RoleMapping {
    @Id
    @Column(name = "role_handle")
    private String roleHandle;

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Override
    public String toString() {
        return roleHandle + "/" + userId;
    }
}
