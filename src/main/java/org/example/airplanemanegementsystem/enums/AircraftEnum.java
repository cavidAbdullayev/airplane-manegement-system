package org.example.airplanemanegementsystem.enums;

import lombok.Getter;

@Getter
public enum AircraftEnum {
    AIRCRAFT_NOT_EXISTS("Aircraft given by id doesn't exists!"),
    AIRCRAFT_NOT_AVAILABLE("Aircraft given by id is nor available!");
    private final String message;

    AircraftEnum(String message) {
        this.message = message;
    }
}