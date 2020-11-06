package com.codecool.queststore.exceptions;

public class MentorNotFoundException extends RuntimeException {

    private final String message;

    public MentorNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}