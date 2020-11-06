package com.codecool.queststore.model;

public enum CourseModule {

    EXTRA("Extra"),
    INTRO("Intro"),
    PROGBASICS("Prog basics"),
    OOP("OOP"),
    WEB("Web"),
    ADVANCED("Advanced"),
    GRADUATE("Graduate");

    private final String name;
    private final int order;

    CourseModule(String moduleName) {
        this.name = moduleName;
        this.order = ordinal();
    }

    public String getName() {
        return name;
    }

    public int getOrder() {return order; }

    public static CourseModule findByIndex(int i) throws IllegalArgumentException {
        try {
            return CourseModule.values()[i];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unknown enum value :"+ i);
        }
    }

    public static CourseModule fromString(String text) {
        for (CourseModule module : CourseModule.values()) {
            if (module.name.equalsIgnoreCase(text)) {
                return module;
            }
        }
        return null;
    }
}
