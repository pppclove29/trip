package com.project.trip.user.entity;

import lombok.Getter;

@Getter
public enum Role {
    TEMP("ROLE_TEMP"),
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String role;

    Role(String role) {
        this.role = role;
    }
}
