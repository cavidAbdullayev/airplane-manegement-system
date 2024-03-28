package org.example.airplanemanegementsystem.enums;
import lombok.Getter;
@Getter
public enum CustomerEnum {
    CUSTOMER_NOT_EXISTS_BY_ID("Customer given by id doesn't exists!"),
    CUSTOMER_EXISTS_BY_EMAIL("Customer given by email already exists!"),
    CUSTOMER_NOT_EXISTS_BY_EMAIL("Customer given by email doesn't exists!"),
    CUSTOMER_EXISTS_BY_FIN("Customer given by FIN already exists!"),
    CUSTOMER_NOT_EXISTS_BY_FIN("Customer given by FIN doesn't exists!"),
    CUSTOMER_EXISTS_BY_PHONE_NUMBER("Customer given by phone number already exists!"),
    CUSTOMER_NOT_EXISTS_BY_PHONE_NUMBER("Customer given by phone number doesn't exists!"),
    INVALID_GENDER("Gender must be 1 or 0!");
    private final String message;

    CustomerEnum(String message) {
        this.message = message;
    }
}