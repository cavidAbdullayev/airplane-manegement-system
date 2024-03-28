package org.example.airplanemanegementsystem.request.aircraft;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAircraftRequest {
    @NotBlank
    @NotNull
    @Size(max = 25)
    String aircraftModel;
    @NotNull
    Short seatQuantity;
    @NotNull
    @NotBlank
    @Size(max = 25)
    String maintenanceStatus;
    @NotNull
    @NotBlank
    @Size(max = 25)
    String availabilityStatus;
    @NotNull
    @NotBlank
    @Size(max = 25)
    String aircraftType;
}