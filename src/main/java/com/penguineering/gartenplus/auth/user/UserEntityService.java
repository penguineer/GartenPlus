package com.penguineering.gartenplus.auth.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserEntityService {
    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserEntityService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
