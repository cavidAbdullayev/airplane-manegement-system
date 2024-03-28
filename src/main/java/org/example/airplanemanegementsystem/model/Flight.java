package org.example.airplanemanegementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(length = 15)
    String departurePoint;
    @Column(length = 15)
    String destinationPoint;
    LocalDateTime departureDate;
    LocalDateTime destinationDate;
    Double price;
    @Builder.Default
    Boolean isEnded = false;
    @Builder.Default
    Boolean isCancelled = false;
    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    Aircraft aircraft;
    @OneToOne(mappedBy = "flight",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    Ticket ticket;
    @OneToMany(mappedBy = "flight",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    List<Personnel> personnel;
}