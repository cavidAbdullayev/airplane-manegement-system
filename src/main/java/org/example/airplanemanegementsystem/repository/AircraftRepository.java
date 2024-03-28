package org.example.airplanemanegementsystem.repository;

import org.example.airplanemanegementsystem.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AircraftRepository extends JpaRepository<Aircraft,Integer> {
    List<Aircraft> findAircraftsByAircraftModel(String model);

    List<Aircraft> findAircraftsByProductionDate(LocalDate date);

    List<Aircraft> findAircraftsByMaintenanceStatus(String status);

    List<Aircraft> findAircraftsByAvailabilityStatus(String status);

    List<Aircraft> findAircraftsByAircraftType(String type);

    List<Aircraft> findAircraftsBySeatQuantity(Short seatQuantity);

    List<Aircraft> findAircraftsByProductionDateBetween(LocalDate startDate,
                                                        LocalDate endDate);
}