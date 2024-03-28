package org.example.airplanemanegementsystem.service;

import org.example.airplanemanegementsystem.request.flight.CreateFlightRequest;
import org.example.airplanemanegementsystem.request.flight.UpdateFlightRequest;
import org.example.airplanemanegementsystem.response.flight.GetFlightResponse;
import org.example.airplanemanegementsystem.response.personnel.GetPersonnelResponse;
import org.example.airplanemanegementsystem.response.ticket.GetTicketResponse;


import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {

    CreateFlightRequest add(CreateFlightRequest request);

    GetFlightResponse getById(Integer id);

    List<GetFlightResponse> getAll();

    GetFlightResponse update(Integer id, UpdateFlightRequest request);

    GetFlightResponse cancel(Integer id);

    List<GetFlightResponse> getFlightsByDeparturePoint(String point);

    List<GetFlightResponse> getFlightsByDestinationPoint(String pint);

    List<GetFlightResponse> getFlightsBetween(String before, String after);

    List<GetFlightResponse> getFlightsByPriceBetween(Double min, Double max);

    List<GetFlightResponse> getFlightsByAircraftType(String type);


    GetFlightResponse markFlightAsComplete(Integer flightId);

    GetFlightResponse cancelFlight(Integer flightId);

    List<GetFlightResponse> getFlightsByDepartureDate(String date);

    List<GetFlightResponse> getFlightsByDestinationDate(String date);
}