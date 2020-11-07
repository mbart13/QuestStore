package com.codecool.queststore.exceptions;

public class ItemNotFoundException extends RuntimeException {

    private final String message;

    public ItemNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}