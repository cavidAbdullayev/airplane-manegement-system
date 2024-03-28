package org.example.airplanemanegementsystem.enums;

import lombok.Getter;

@Getter
public enum TicketEnum {
    SEAT_ALREADY_ORDERED("Seat given by number already ordered!"),
    TICKET_SALES_SUSPENDED("Ticket sales are suspended!"),
    TICKET_NOT_EXISTS_BY_ID("Ticket given by id doesn't exists!");
    private final String message;

    TicketEnum(String message) {
        this.message = message;
    }
}