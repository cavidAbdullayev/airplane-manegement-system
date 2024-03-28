package org.example.airplanemanegementsystem.business;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.example.airplanemanegementsystem.enums.TicketEnum;
import org.example.airplanemanegementsystem.exceptions.GeneralException;
import org.example.airplanemanegementsystem.model.Ticket;
import org.example.airplanemanegementsystem.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketBusiness {
    private final TicketRepository ticketRepository;

    public void checkSeatNumber(Integer flightId, Short seatNumber) {
        if (ticketRepository.checkIfSeatNumberExistsByFlightId(flightId,
                seatNumber))
            throw new GeneralException(TicketEnum.SEAT_ALREADY_ORDERED);
    }

    public void checkTime(LocalDateTime departureDate) {
        if (Duration.between(departureDate, LocalDateTime.now()).toMinutes() <= 15)
            throw new GeneralException(TicketEnum.TICKET_SALES_SUSPENDED);
    }
}