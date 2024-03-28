package org.example.airplanemanegementsystem.response.personnel;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetPersonnelResponse {
    String firstName;
    String lastName;
    String position;
    String birthdate;
    String gender;
    String hireDate;
    Double salary;
    Integer flightId;
}