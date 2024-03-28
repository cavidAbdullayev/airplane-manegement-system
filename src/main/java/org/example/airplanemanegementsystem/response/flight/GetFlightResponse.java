package org.example.airplanemanegementsystem.response.flight;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetFlightResponse {
    String departurePoint;
    String destinationPoint;
    String departureDate;
    String destinationDate;
    Double price;
    Integer aircraftId;
}