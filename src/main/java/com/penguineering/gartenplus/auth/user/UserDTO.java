package com.penguineering.gartenplus.auth.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.server.WebSession;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record UserDTO(
        @JsonProperty("id") UUID id,
        @JsonProperty("display_name") String displayName,
        @JsonProperty("email") String email,
        @JsonProperty("avatar_url") URI avatarUrl) {

    @Override
    public String toString() {
        return displayName + "(" + id + ")";
    }

    @JsonIgnore
    public boolean isNew() {
        return Objects.isNull(id);
    }

    public static Optional<UserDTO> fromWebSession(WebSession session) {
        return Optional.ofNullable(session.getAttribute("user"))
                .filter(user -> user instanceof UserDTO)
                .map(UserDTO.class::cast);
    }

    public Optional<UserDTO> saveToWebSession(WebSession session) {
        return Optional.ofNullable(session.getAttributes().put("user", this))
                .filter(user -> user instanceof UserDTO)
                .map(UserDTO.class::cast);
    }
}
