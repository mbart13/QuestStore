package com.codecool.queststore.model;

public enum Role {

    ADMIN("role_admin"),
    MENTOR("role_mentor"),
    STUDENT("role_student");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
