package com.penguineering.gartenplus.auth.group;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface GroupRepository extends CrudRepository<GroupEntity, UUID> {
    Optional<GroupEntity> findByName(String name);
}
