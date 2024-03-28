package org.example.airplanemanegementsystem.repository;

import lombok.NonNull;
import org.example.airplanemanegementsystem.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Integer> {
    boolean existsById(@NonNull Integer id);

    List<Flight> findFlightsByAircraftId(Integer aircraftId);

    List<Flight> findFlightsByDeparturePoint(String point);

    List<Flight> findFlightsByDestinationPoint(String point);

    List<Flight> findFlightsByDepartureDateBetween(LocalDateTime before, LocalDateTime after);

    List<Flight> findFlightsByPriceBetween(Double min, Double max);

    List<Flight> findFlightsByAircraft_AircraftType(String type);

    List<Flight> findFlightsByDepartureDate(LocalDateTime date);

    List<Flight> findFlightsByDestinationDate(LocalDateTime date);
}