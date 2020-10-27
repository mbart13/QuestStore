package com.codecool.queststore.model;

public enum Role {

    ADMIN("ROLE_ADMIN"),
    MENTOR("ROLE_MENTOR"),
    STUDENT("ROLE_STUDENT");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
