package com.penguineering.gartenplus.auth.role;

import com.penguineering.gartenplus.auth.user.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SystemRoleService {
    private static final Map<String, SystemRole> HANDLES;

    static {
        HANDLES = Arrays.stream(SystemRole.values())
                .map(role -> Map.entry(role.getHandle(), role))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private final RoleMappingRepository roleMappingRepository;
    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public SystemRoleService(
            RoleMappingRepository roleMappingRepository,
            UserRepository userRepository) {
        this.roleMappingRepository = roleMappingRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Set<SystemRole> getRolesForUser(UUID userId) {
        return Optional.ofNullable(userId)
                .filter(userRepository::existsById)
                .map(roleMappingRepository::findByUserId)
                .map(l -> l.stream()
                        .map(RoleMapping::getRoleHandle)
                        // ignore unknown handles
                        .filter(HANDLES.keySet()::contains)
                        .map(HANDLES::get)
                        .collect(Collectors.toSet()))
                .orElse(Set.of());
    }

    @Transactional
    public void setRoleForUser(SystemRole role, UUID userId) {
        var mapping = new RoleMapping(role.getHandle(), userId);
        roleMappingRepository.save(mapping);
    }

    @Transactional
    public void clearRoleForUser(SystemRole role, UUID userId) {
        var mapping = new RoleMapping(role.getHandle(), userId);
        roleMappingRepository.delete(mapping);
    }

    @Transactional
    public void updateRoles(UUID userId, Set<SystemRole> roles) {
        var currentRoles = getRolesForUser(userId);

        var toAdd = new HashSet<>(roles);
        toAdd.removeAll(currentRoles);
        toAdd.forEach(role -> setRoleForUser(role, userId));

        var toRemove = new HashSet<>(currentRoles);
        toRemove.removeAll(roles);
        toRemove.forEach(role -> clearRoleForUser(role, userId));
    }
}
