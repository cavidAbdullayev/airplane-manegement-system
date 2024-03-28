package org.example.airplanemanegementsystem.enums;

import lombok.Getter;

@Getter
public enum FlightEnum {
    FLIGHT_CANCELLED("Flight given by id already cancelled!"),
    FLIGHT_ENDED("Flight given by id has been ended!"),
    FLIGHT_NOT_EXISTS_BY_ID("Flight given by id doesn't exists!"),
    FLIGHT_NOT_START("Flight given by id doesn't start!");
    private final String message;

    FlightEnum(String message) {
        this.message = message;
    }
}