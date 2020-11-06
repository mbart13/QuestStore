package com.codecool.queststore.model;

public enum Module {

    INTRO("Intro"),
    PROGBASICS("Prog basics"),
    OOP("OOP"),
    ADVANCED("Advanced"),
    GRADUATE("Graduate"),
    EXTRA("Extra");

    private final String moduleName;
    private final int order;

    Module(String moduleName) {
        this.moduleName = moduleName;
        this.order = ordinal();
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getOrder() {return order; }

    public Module findByIndex(int i) throws IllegalArgumentException {
        try {
            return Module.values()[i];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unknown enum value :"+ i);
        }
    }
}
