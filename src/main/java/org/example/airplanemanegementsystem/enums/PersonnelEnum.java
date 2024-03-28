package org.example.airplanemanegementsystem.enums;

import lombok.Getter;

@Getter
public enum PersonnelEnum {
    PERSONNEL_NOT_EXISTS_BY_ID("Personnel given by id doesn't exists!");
    private final String message;

    PersonnelEnum(String message) {
        this.message = message;
    }
}