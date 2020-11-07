package com.codecool.queststore.model;

public enum Role {

    ADMIN("ADMIN", "ROLE_ADMIN"),
    MENTOR("MENTOR", "ROLE_MENTOR"),
    STUDENT("STUDENT", "ROLE_STUDENT");

    private final String roleName;
    private final String dbName;

    Role(String roleName, String dbName) {
        this.roleName = roleName;
        this.dbName = dbName;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDbName() {
        return dbName;
    }
}
