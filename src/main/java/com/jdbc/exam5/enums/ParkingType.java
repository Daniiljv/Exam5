package com.jdbc.exam5.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ParkingType {
    STANDARD("Standard"),
    FOR_DISABLED("For invalids"),
    FAMILIES("For family cars"),
    ELECTRIC_CARS("For electric cars");

    final String description;
}
