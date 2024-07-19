package com.penguineering.gartenplus.auth.group;

import com.penguineering.gartenplus.auth.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "systemgroup")
public class GroupEntity extends AbstractPersistable<UUID> {
    String name;

    String description;

    @ManyToMany(
            mappedBy = "groups",
            fetch = jakarta.persistence.FetchType.LAZY
    )
    List<UserEntity> users;

    public GroupDTO toDTO() {
        return new GroupDTO(getId(), name, description);
    }
}
