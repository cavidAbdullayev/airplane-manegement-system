package org.example.airplanemanegementsystem.request.personnel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePersonnelRequest {
    @Size(max = 20)
    @NotNull
    @NotBlank
    String firstName;
    @Size(max = 20)
    @NotNull
    @NotBlank
    String lastName;
    @Size(max = 15)
    @NotNull
    @NotBlank
    String position;
    @NotNull
    @NotBlank
    String birthdate;
    @NotNull
    Byte gender;
    @NotNull
    @NotBlank
    String hireDate;
    @NotNull
    Double salary;
    @NotNull
    Integer flightId;
}