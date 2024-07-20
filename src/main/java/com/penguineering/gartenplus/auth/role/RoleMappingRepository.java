package com.penguineering.gartenplus.auth.role;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface RoleMappingRepository extends CrudRepository<RoleMapping, RoleMappingKey> {
    List<RoleMapping> findByUserId(UUID userId);
}
