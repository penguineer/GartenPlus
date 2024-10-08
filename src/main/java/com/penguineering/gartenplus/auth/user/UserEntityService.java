package com.penguineering.gartenplus.auth.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class UserEntityService {
    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserEntityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<UserDTO> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(UserEntity::toDTO)
                .toList();
    }

    @Transactional
    public Optional<UserEntity> getUser(UUID userId) {
        return Optional
                .ofNullable(userId)
                .flatMap(userRepository::findById);
    }

    @Transactional
    public Optional<UserEntity> getUserWithGroups(UUID userId) {
        return userRepository
                .findById(userId)
                .map(u -> {
                    Hibernate.initialize(u.getGroups());
                    return u;
                });
    }

    @Transactional
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Transactional
    public UserDTO saveDTO(UserDTO user) {
        return Optional.ofNullable(user)
                .map(UserDTO::id)
                //load or create entity
                .flatMap(id -> Optional.of(id)
                        .map(userRepository::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .or(() -> Optional.of(new UserEntity())))
                // update entity
                .map(e -> {
                    e.setDisplayName(user.displayName());
                    e.setEmail(user.email());
                    e.setAvatarUrl(user.avatarUrl());
                    return e;
                })
                // save entity
                .map(userRepository::save)
                .map(UserEntity::toDTO)
                .orElse(null);
    }
}
