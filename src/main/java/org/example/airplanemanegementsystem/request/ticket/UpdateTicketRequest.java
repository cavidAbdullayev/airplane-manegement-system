package org.example.airplanemanegementsystem.request.ticket;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTicketRequest {

    Integer flightId;
    Short seatNumber;
    Double price;

}