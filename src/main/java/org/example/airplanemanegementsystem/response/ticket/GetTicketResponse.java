package org.example.airplanemanegementsystem.response.ticket;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class GetTicketResponse {
    Integer flightId;
    String customerFullName;
    Short seatNumber;
    Double price;
    Integer customerId;
    String purchaseDate;
}