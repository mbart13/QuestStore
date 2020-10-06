package com.codecool.queststore.exception;

import static java.lang.String.format;

public class EntityNotFoundException<T> extends RuntimeException {
    public EntityNotFoundException(int id, Class<T> missingClass) {
        super(format("%s not found for given: %n ", missingClass.getName(), id));
    }
}
