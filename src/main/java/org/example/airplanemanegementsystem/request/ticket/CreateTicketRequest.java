package org.example.airplanemanegementsystem.request.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTicketRequest {
    @NotNull
    Integer flightId;
    @NotNull
    Short seatNumber;
    @NotNull
    Integer customerId;
}