package org.example.airplanemanegementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



@Entity
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(length = 10, unique = true)
    String FIN;
    @Column(length = 20)
    String firstName;
    @Column(length = 20)
    String lastName;
    Byte gender;
    @Column(length = 35, unique = true)
    String email;
    @Column(length = 15)
    String phoneNumber;
    String address;
    LocalDate birthdate;
    @OneToOne(mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Ticket ticket;

    public void setGender(Byte gender) {
        if (gender == null)
            return;
        if (gender != 1 && gender != 0)
            throw new RuntimeException("Gender must be 1 or 0");
        this.gender = gender;
    }

    public String getGender() {
        return gender == 0 ? "Women" : "Men";
    }
}