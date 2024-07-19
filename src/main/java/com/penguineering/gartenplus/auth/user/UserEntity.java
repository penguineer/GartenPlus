package com.penguineering.gartenplus.auth.user;

import com.penguineering.gartenplus.auth.group.GroupEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "user")
public class UserEntity extends AbstractPersistable<UUID> {
    String displayName;

    String email;

    URI avatarUrl;

    @ManyToMany(
            fetch = jakarta.persistence.FetchType.LAZY
    )
    List<GroupEntity> groups;

    public static UserEntity fromDTO(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDTO.id());
        userEntity.setDisplayName(userDTO.displayName());
        userEntity.setEmail(userDTO.email());
        userEntity.setAvatarUrl(userDTO.avatarUrl());

        return userEntity;
    }

    public UserDTO toDTO() {
        return new UserDTO(getId(), displayName, email, avatarUrl);
    }
}
