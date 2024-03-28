package org.example.airplanemanegementsystem.business;

import lombok.RequiredArgsConstructor;
import org.example.airplanemanegementsystem.enums.FlightEnum;
import org.example.airplanemanegementsystem.exceptions.GeneralException;
import org.example.airplanemanegementsystem.model.Flight;
import org.example.airplanemanegementsystem.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class FlightBusiness {
    private final FlightRepository flightRepository;

    public void checkFlightAndThrow(Flight flight) {
        if (flight.getIsCancelled())
            throw new GeneralException(FlightEnum.FLIGHT_CANCELLED);
        if (flight.getIsEnded())
            throw new GeneralException(FlightEnum.FLIGHT_ENDED);
        if (Duration.between(LocalDateTime.now(), flight.getDestinationDate()).getSeconds() < 0) {
            flight.setIsEnded(true);
            flightRepository.save(flight);
            throw new GeneralException(FlightEnum.FLIGHT_CANCELLED);
        }
    }

    public Flight checkFlight(Flight flight) {
        if (Duration.between(LocalDateTime.now(),
                flight.getDestinationDate()).getSeconds() < 0) {
            flight.setIsEnded(true);
            flightRepository.save(flight);
        }
        return flight;
    }

    public void checkFlightExistsById(Integer id) {
        if (flightRepository.existsById(id))
            throw new GeneralException(FlightEnum.FLIGHT_NOT_EXISTS_BY_ID);
    }
}