package com.penguineering.gartenplus.auth.role;

import lombok.Getter;

@Getter
public enum SystemRole {
    ADMINISTRATOR("administrator", "Administrator"),
    MEMBER("member", "Gartenfreund"),
    FRIEND("friend", "Gartenfreund-Freund"),
    TREASURER("treasurer", "Geldhüter");

    private final String handle;
    private final String displayName;

    SystemRole(String handle, String displayName) {
        this.handle = handle;
        this.displayName = displayName;
    }

    public final String asSpringRole() {
        return "ROLE_" + handle.toUpperCase();
    }
}
