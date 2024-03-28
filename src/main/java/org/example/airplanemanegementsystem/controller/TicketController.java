package org.example.airplanemanegementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.airplanemanegementsystem.request.ticket.CreateTicketRequest;
import org.example.airplanemanegementsystem.request.ticket.UpdateTicketRequest;
import org.example.airplanemanegementsystem.response.ticket.GetTicketResponse;
import org.example.airplanemanegementsystem.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    public GetTicketResponse add(@RequestBody CreateTicketRequest request) {
        return ticketService.add(request);
    }

    @GetMapping("/{id}")
    public GetTicketResponse getById(@PathVariable Integer id) {
        return ticketService.getById(id);
    }

    @GetMapping("get-all")
    public List<GetTicketResponse> getAll() {
        return ticketService.getAll();
    }

    @PutMapping("/{id}")
    public GetTicketResponse update(@PathVariable Integer id,
                                    @RequestBody UpdateTicketRequest request) {
        return ticketService.update(id, request);
    }

    @GetMapping("/get-by-flight-id/{id}")
    public List<GetTicketResponse> getTicketsByFlightId(@PathVariable Integer id) {
        return ticketService.getTicketsByFlightId(id);
    }

    @GetMapping("/get-by-customer-full-name")
    public List<GetTicketResponse> getTicketsByCustomerFullName(@RequestParam("fullName") String fullName) {
        return ticketService.getTicketsByCustomerFullName(fullName);
    }

    @GetMapping("/get-by-seat-number/{flightId}")
    public GetTicketResponse getTicketBySeatNumber(@PathVariable Integer flightId,
                                                   @RequestParam("seatNumber") Short seatNumber) {
        return ticketService.getTicketBySeatNumber(flightId, seatNumber);
    }

    @GetMapping("/get-tickets-by-price")
    public List<GetTicketResponse> getTicketsByPrice(@RequestParam("price") Double price) {
        return ticketService.getTicketsByPrice(price);
    }

    @GetMapping("/get-by-purchase-date")
    public List<GetTicketResponse> getTicketsByPurchaseDate(@RequestParam("date") String date) {
        return ticketService.getTicketsByPurchaseDate(date);
    }

    @GetMapping("/get-ticket-by-customer-id/{customerId}")
    public GetTicketResponse getTicketByCustomerId(@PathVariable Integer customerId) {
        return ticketService.getTicketByCustomerId(customerId);
    }

    @GetMapping("/get-tickets-before-purchase-date")
    public List<GetTicketResponse> getTicketsBeforePurchaseDate(@RequestParam String date) {
        return ticketService.getTicketsBeforePurchaseDate(date);
    }

    @GetMapping("/get-tickets-after-purchase-date")
    public List<GetTicketResponse> getTicketsAfterPurchaseDate(@RequestParam String date) {
        return ticketService.getTicketsAfterPurchaseDate(date);
    }

    @GetMapping("/get-tickets-by-customer-age/{age}")
    public List<GetTicketResponse> getTicketsByCustomerAge(@PathVariable Integer age) {
        return ticketService.getTicketsByCustomerAge(age);
    }

    @GetMapping("/get-tickets-by-flight-departure-date")
    public List<GetTicketResponse> getTicketsByFlightDepartureDate(@RequestParam String date) {
        return ticketService.getTicketsByFlightDepartureDate(date);
    }

    @GetMapping("/get-tickets-by-flight-destination-date")
    public List<GetTicketResponse> getTicketsByFlightDestinationDate(@RequestParam String date) {
        return ticketService.getTicketsByFlightDestinationDate(date);
    }

    @GetMapping("/get-tickets-by-customer-address")
    public List<GetTicketResponse> getTicketsByCustomerAddress(@RequestParam String address) {
        return ticketService.getTicketsByCustomerAddress(address);
    }

    @GetMapping("/get-ticket-by-customer-email")
    public GetTicketResponse getTicketByCustomerEmail(@RequestParam String email) {
        return ticketService.getTicketByCustomerEmail(email);
    }
}