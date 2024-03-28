package org.example.airplanemanegementsystem.request.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateCustomerRequest {
    String FIN;
    String firstName;
    String lastName;
    Byte gender;
    String email;
    String phoneNumber;
    String address;
    String birthdate;
}