package org.example.airplanemanegementsystem.request.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCustomerRequest {
    @NotNull
    @NotBlank
    String FIN;
    @NotNull
    @NotBlank
    @Size(max = 20)
    String firstName;
    @NotNull
    @NotBlank
    @Size(max = 20)
    String lastName;
    @NotNull
    Byte gender;
    @NotNull
    @NotBlank
    @Size(max = 35)
    String email;
    @NotNull
    @NotBlank
    @Size(max = 15)
    String phoneNumber;
    @NotNull
    @NotBlank
    String address;
    @NotNull
    @NotBlank
    String birthdate;
}