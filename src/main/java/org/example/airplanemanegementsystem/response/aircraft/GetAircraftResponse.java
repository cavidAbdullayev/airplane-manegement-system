package org.example.airplanemanegementsystem.response.aircraft;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetAircraftResponse {
    String aircraftModel;
    Short seatQuantity;
    String productionDate;
    String maintenanceStatus;
    String availabilityStatus;
    String aircraftType;
}