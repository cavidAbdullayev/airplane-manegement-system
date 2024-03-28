package org.example.airplanemanegementsystem.request.flight;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateFlightRequest {
    String departurePoint;
    String destinationPoint;
    String departureTime;
    String destinationTime;
    Double price;
    Integer aircraftId;
}