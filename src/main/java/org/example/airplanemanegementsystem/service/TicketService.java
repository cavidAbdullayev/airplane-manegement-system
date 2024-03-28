package org.example.airplanemanegementsystem.service;

import org.example.airplanemanegementsystem.request.ticket.CreateTicketRequest;
import org.example.airplanemanegementsystem.request.ticket.UpdateTicketRequest;
import org.example.airplanemanegementsystem.response.ticket.GetTicketResponse;

import java.util.List;

public interface TicketService {

    GetTicketResponse add(CreateTicketRequest request);

    GetTicketResponse getById(Integer id);

    List<GetTicketResponse> getAll();

    GetTicketResponse update(Integer id, UpdateTicketRequest request);

    List<GetTicketResponse> getTicketsByFlightId(Integer id);

    List<GetTicketResponse> getTicketsByCustomerFullName(String fullName);

    GetTicketResponse getTicketBySeatNumber(Integer flightId, Short seatNumber);

    List<GetTicketResponse> getTicketsByPrice(Double price);

    List<GetTicketResponse> getTicketsByPurchaseDate(String date);

    GetTicketResponse getTicketByCustomerId(Integer customerId);

    List<GetTicketResponse> getTicketsBeforePurchaseDate(String date);

    List<GetTicketResponse> getTicketsAfterPurchaseDate(String date);

    List<GetTicketResponse> getTicketsByCustomerAge(Integer age);

    List<GetTicketResponse> getTicketsByFlightDepartureDate(String date);

    List<GetTicketResponse> getTicketsByFlightDestinationDate(String date);

    List<GetTicketResponse> getTicketsByCustomerAddress(String address);

    GetTicketResponse getTicketByCustomerEmail(String email);
}