package com.codecool.queststore.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RankNotFoundException extends RuntimeException {
    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}
