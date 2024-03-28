package org.example.airplanemanegementsystem.request.flight;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateFlightRequest {
    @NotNull
    @NotBlank
    @Size(max = 15)
    String departurePoint;
    @NotNull
    @NotBlank
    @Size(max = 15)
    String destinationPoint;
    @NotNull
    @NotBlank
    String departureTime;
    @NotNull
    @NotBlank
    String destinationTime;
    @NotNull
    Double price;
    @NotNull
    Integer aircraftId;
}