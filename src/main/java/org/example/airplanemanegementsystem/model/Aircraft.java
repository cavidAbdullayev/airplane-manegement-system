package org.example.airplanemanegementsystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(length = 25)
    String aircraftModel;
    Short seatQuantity;
    @CreationTimestamp
    LocalDate productionDate;
    @Column(length = 25)
    String maintenanceStatus;
    @Column(length = 25)
    String availabilityStatus;
    @Column(length = 25)
    String aircraftType;
    @OneToMany(mappedBy = "aircraft",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<Flight> flights;
}