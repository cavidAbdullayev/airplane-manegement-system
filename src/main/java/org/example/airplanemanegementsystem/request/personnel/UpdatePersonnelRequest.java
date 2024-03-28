package org.example.airplanemanegementsystem.request.personnel;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePersonnelRequest {
    String firstName;
    String lastName;
    String position;
    String birthdate;
    String gender;
    String hireDate;
    Double salary;
    Integer flightId;
}