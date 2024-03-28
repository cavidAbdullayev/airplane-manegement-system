package org.example.airplanemanegementsystem.request.aircraft;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateAircraftRequest {
    String aircraftModel;
    Short seatQuantity;
    String productionDate;
    String maintenanceStatus;
    String availabilityStatus;
    String aircraftType;
}