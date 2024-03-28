package org.example.airplanemanegementsystem.repository;

import org.example.airplanemanegementsystem.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    @Query("SELECT CASE WHEN COUNT(t) > 0 " +
            "THEN TRUE ELSE FALSE END " +
            "FROM Ticket t " +
            "WHERE t.flight.id = :flightId " +
            "AND t.seatNumber = :targetSeatNumber")
    boolean checkIfSeatNumberExistsByFlightId(@Param("flightId") Integer flightId, @Param("targetSeatNumber") Short targetSeatNumber);

    List<Ticket> findAllByFlight_Id(Integer flightId);

    List<Ticket> findAllByCustomerFirstNameAndCustomerLastName(String firstName, String lastName);

    Ticket findBySeatNumber(Short seatNumber);

    List<Ticket> findAllByPrice(Double price);

    List<Ticket> findAllByPurchaseDate(LocalDate date);

    Ticket findByCustomerId(Integer customerId);

    List<Ticket> findAllByPurchaseDateBefore(LocalDate date);

    List<Ticket> findAllByPurchaseDateAfter(LocalDate date);

    List<Ticket> findAllByCustomer_Birthdate(LocalDate date);

    List<Ticket> findAllByFlight_DepartureDate(LocalDateTime date);

    List<Ticket> findAllByFlightDestinationDate(LocalDateTime date);

    List<Ticket> findAllByCustomer_Address(String address);

    Ticket findByCustomerEmail(String email);
}