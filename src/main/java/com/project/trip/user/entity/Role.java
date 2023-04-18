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

    /*
    <Post Auth>
    save
        - admin
            - accept all
        - user
            - non notice post
        - anonymous
            - deny all
    update
        - admin
            - every notice post
            - own post
        - user
            - own post
        - anonymous
            - deny all
    delete
        - admin
            - accept all
        - user
            - own post
        - anonymous
            - deny all
    view
        - admin
            - accept all
        - user
            - accept all
        - anonymous
            - accept all
     */
}
