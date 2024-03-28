package org.example.airplanemanegementsystem.response.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetCustomerResponse {

    String FIN;
    String firstName;
    String lastName;
    String gender;
    String email;
    String phoneNumber;
    String address;
    String birthdate;

}