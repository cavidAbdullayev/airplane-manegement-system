package org.example.airplanemanegementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.airplanemanegementsystem.business.CustomerBusiness;
import org.example.airplanemanegementsystem.business.FlightBusiness;
import org.example.airplanemanegementsystem.business.TicketBusiness;
import org.example.airplanemanegementsystem.enums.CustomerEnum;
import org.example.airplanemanegementsystem.enums.FlightEnum;
import org.example.airplanemanegementsystem.enums.TicketEnum;
import org.example.airplanemanegementsystem.exceptions.GeneralException;
import org.example.airplanemanegementsystem.mappers.TicketMapper;
import org.example.airplanemanegementsystem.model.Customer;
import org.example.airplanemanegementsystem.model.Flight;
import org.example.airplanemanegementsystem.model.Ticket;
import org.example.airplanemanegementsystem.repository.CustomerRepository;
import org.example.airplanemanegementsystem.repository.FlightRepository;
import org.example.airplanemanegementsystem.repository.TicketRepository;
import org.example.airplanemanegementsystem.request.ticket.CreateTicketRequest;
import org.example.airplanemanegementsystem.request.ticket.UpdateTicketRequest;
import org.example.airplanemanegementsystem.response.ticket.GetTicketResponse;
import org.example.airplanemanegementsystem.service.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final CustomerRepository customerRepository;
    private final FlightRepository flightRepository;
    private final FlightBusiness flightBusiness;
    private final CustomerBusiness customerBusiness;
    private final TicketBusiness ticketBusiness;

    @Override
    @Transactional
    public GetTicketResponse add(CreateTicketRequest request) {
        Ticket ticket = ticketMapper.mapToTicketFromCreateTicketRequest(request);
        ticketBusiness.checkSeatNumber(request.getFlightId(), request.getSeatNumber());
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() ->
                        new GeneralException(CustomerEnum.CUSTOMER_NOT_EXISTS_BY_ID));
        ticket.setCustomerFullName(customer.getFirstName() + " " + customer.getLastName());
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new GeneralException(FlightEnum.FLIGHT_NOT_EXISTS_BY_ID));
        ticketBusiness.checkTime(flight.getDepartureDate());
        ticket.setFlight(flight);
        ticket.setCustomer(customer);
        ticket.setPrice(flight.getPrice() * 1.2);
        ticketRepository.save(ticket);
        return ticketMapper.mapToGetTicketResponse(ticket);
    }

    @Override
    public GetTicketResponse getById(Integer id) {
        return ticketMapper.mapToGetTicketResponse(ticketRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException(TicketEnum.TICKET_NOT_EXISTS_BY_ID)));
    }

    @Override
    public List<GetTicketResponse> getAll() {
        return ticketRepository.findAll()
                .stream()
                .map(ticketMapper::mapToGetTicketResponse)
                .toList();
    }

    @Override
    @Transactional
    public GetTicketResponse update(Integer id, UpdateTicketRequest request) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new GeneralException(TicketEnum.TICKET_NOT_EXISTS_BY_ID));
        ticketMapper.mapToTicketFromUpdateTicketRequest(ticket, request);
        if (request.getFlightId() != null) {
            ticket.setFlight(flightRepository.findById(request.getFlightId())
                    .orElseThrow(() -> new GeneralException(TicketEnum.TICKET_NOT_EXISTS_BY_ID)));
        }
        ticketRepository.save(ticket);
        return ticketMapper.mapToGetTicketResponse(ticket);
    }

    @Override
    public List<GetTicketResponse> getTicketsByFlightId(Integer flightId) {
        flightBusiness.checkFlightExistsById(flightId);
        return ticketRepository.findAllByFlight_Id(flightId)
                .stream()
                .map(ticketMapper::mapToGetTicketResponse)
                .toList();
    }

    @Override
    public List<GetTicketResponse> getTicketsByCustomerFullName(String fullName) {
        String[] info = fullName.split(" ");
        return ticketRepository.findAllByCustomerFirstNameAndCustomerLastName(
                        info[0], info[1])
                .stream()
                .map(ticketMapper::mapToGetTicketResponse)
                .toList();
    }

    @Override
    public GetTicketResponse getTicketBySeatNumber(Integer flightId, Short seatNumber) {
        flightBusiness.checkFlightExistsById(flightId);
        ticketBusiness.checkSeatNumber(flightId, seatNumber);
        return ticketMapper.mapToGetTicketResponse(
                ticketRepository.findBySeatNumber(seatNumber));
    }

    @Override
    public List<GetTicketResponse> getTicketsByPrice(Double price) {
        return ticketRepository.findAllByPrice(price)
                .stream()
                .map(ticketMapper::mapToGetTicketResponse)
                .toList();
    }

    @Override
    public List<GetTicketResponse> getTicketsByPurchaseDate(String date) {
        return ticketRepository.findAllByPurchaseDate(
                        LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .stream()
                .map(ticketMapper::mapToGetTicketResponse)
                .toList();
    }

    @Override
    public GetTicketResponse getTicketByCustomerId(Integer customerId) {
        customerBusiness.checkExistsById(customerId);
        return ticketMapper.mapToGetTicketResponse(
                ticketRepository.findByCustomerId(customerId));
    }

    @Override
    public List<GetTicketResponse> getTicketsBeforePurchaseDate(String date) {
        return ticketRepository.findAllByPurchaseDateBefore(
                        LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .stream()
                .map(ticketMapper::mapToGetTicketResponse)
                .toList();
    }

    @Override
    public List<GetTicketResponse> getTicketsAfterPurchaseDate(String date) {
        return ticketRepository.findAllByPurchaseDateAfter(
                        LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .stream()
                .map(ticketMapper::mapToGetTicketResponse)
                .toList();
    }

    @Override
    public List<GetTicketResponse> getTicketsByCustomerAge(Integer age) {
        return ticketRepository.findAllByCustomer_Birthdate(
                        LocalDate.now().minusYears(age))
                .stream()
                .map(ticketMapper::mapToGetTicketResponse)
                .toList();
    }

    @Override
    public List<GetTicketResponse> getTicketsByFlightDepartureDate(String date) {
        return ticketRepository.findAllByFlight_DepartureDate(
                        LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss")))
                .stream()
                .map(ticketMapper::mapToGetTicketResponse)
                .toList();
    }

    @Override
    public List<GetTicketResponse> getTicketsByFlightDestinationDate(String date) {
        return ticketRepository.findAllByFlightDestinationDate(
                        LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss")))
                .stream()
                .map(ticketMapper::mapToGetTicketResponse)
                .toList();
    }

    @Override
    public List<GetTicketResponse> getTicketsByCustomerAddress(String address) {
        return ticketRepository.findAllByCustomer_Address(address)
                .stream()
                .map(ticketMapper::mapToGetTicketResponse)
                .toList();
    }

    @Override
    public GetTicketResponse getTicketByCustomerEmail(String email) {
        customerBusiness.checkNotExistsByEmail(email);
        return ticketMapper.mapToGetTicketResponse(ticketRepository
                .findByCustomerEmail(email));
    }
}