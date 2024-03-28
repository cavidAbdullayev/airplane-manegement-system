package org.example.airplanemanegementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    Flight flight;
    @Column(length = 41)
    String customerFullName;
    Short seatNumber;
    Double price;
    @CreationTimestamp
    LocalDate purchaseDate;
    @OneToOne
    @JoinColumn(name = "customer_id")
    Customer customer;
}