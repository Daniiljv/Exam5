package com.jdbc.exam5.enams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor

public enum Status {
    IS_EMPTY("Is empty"),
    RESERVED("Reserved"),
    NOT_EMPTY("Is not empty");

    private final String description;
}
