package com.penguineering.gartenplus.auth.user;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    UserEntity getOne(UUID uuid);
}