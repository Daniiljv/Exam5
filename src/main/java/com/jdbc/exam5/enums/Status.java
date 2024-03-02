package com.jdbc.exam5.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    AVAILABLE("AVAILABLE"),
    RESERVED("RESERVED"),
    NOT_AVAILABLE("NOT_AVAILABLE");

    final String description;
}
