package com.penguineering.gartenplus.auth.mapping;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"issuer", "oidcId"})
@IdClass(OidcMappingKey.class)
@Table(name = "oidc_mapping")
public class OidcMapping {
    @Id
    @Column(name = "issuer")
    private String issuer;

    @Id
    @Column(name = "oidc_id")
    private String oidcId;

    @Column(name = "user_id")
    private UUID userId;

    @Override
    public String toString() {
        return issuer + "/" + oidcId + " -> " + userId;
    }
}