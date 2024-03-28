package org.example.airplanemanegementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(length = 20)
    String firstName;
    @Column(length = 20)
    String lastName;
    @Column(length = 15)
    String position;
    LocalDate birthdate;
    Byte gender;
    @CreatedDate
    LocalDate hireDate;
    Double salary;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    Flight flight;

    public String getGender() {
        return gender == 1 ? "Men" : "Women";
    }

    public void setGender(Byte gender) {
        if (gender == null)
            return;
        if (gender != 1 && gender != 0)
            throw new RuntimeException("Gender must be 1 or 0");
        this.gender = gender;
    }
}