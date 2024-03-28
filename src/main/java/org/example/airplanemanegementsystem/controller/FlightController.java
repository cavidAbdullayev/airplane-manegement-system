package org.example.airplanemanegementsystem.controller;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.airplanemanegementsystem.request.aircraft.UpdateAircraftRequest;
import org.example.airplanemanegementsystem.request.flight.CreateFlightRequest;
import org.example.airplanemanegementsystem.request.flight.UpdateFlightRequest;
import org.example.airplanemanegementsystem.response.flight.GetFlightResponse;
import org.example.airplanemanegementsystem.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    @GetMapping("/{id}")
    public GetFlightResponse getById(@PathVariable Integer id) {
        return flightService.getById(id);
    }

    @GetMapping("/get-all")
    public List<GetFlightResponse> getAll() {
        return flightService.getAll();
    }

    @PostMapping
    public CreateFlightRequest add(@RequestBody @Valid CreateFlightRequest request) {
        return flightService.add(request);
    }

    @PutMapping("/{id}")
    public GetFlightResponse update(@PathVariable Integer id,
                                    @RequestBody @Valid UpdateFlightRequest request) {
        return flightService.update(id, request);
    }

    @PutMapping("/cancel/{id}")
    public GetFlightResponse cancel(@PathVariable Integer id) {
        return flightService.cancel(id);
    }

    @GetMapping("/get-by-departure-point")
    public List<GetFlightResponse> getFlightsByDeparturePoint(@RequestParam("point") String point) {
        return flightService.getFlightsByDeparturePoint(point);
    }

    @GetMapping("/get-by-destination-point")
    public List<GetFlightResponse> getFlightsByDestinationPoint(@RequestParam("point") String point) {
        return flightService.getFlightsByDestinationPoint(point);
    }

    @GetMapping("/get-flights-between")
    public List<GetFlightResponse> getFlightsBetween(@RequestParam("before") String before,
                                                     @RequestParam("after") String after) {
        return flightService.getFlightsBetween(before, after);
    }

    @GetMapping("/get-flights-by-price-between")
    public List<GetFlightResponse> getFlightsByPriceBetween(@RequestParam("min") Double min,
                                                            @RequestParam("max") Double max) {
        return flightService.getFlightsByPriceBetween(min, max);
    }

    @GetMapping("/get-flights-by-aircraft-type")
    public List<GetFlightResponse> getFlightsByAircraftType(@RequestParam("type") String type) {
        return flightService.getFlightsByAircraftType(type);
    }

    @GetMapping("/mark-flight-as-complete/{id}")
    GetFlightResponse markFlightAsComplete(@PathVariable Integer id) {
        return flightService.markFlightAsComplete(id);
    }

    @GetMapping("/cancel-flight/{id}")
    GetFlightResponse cancelFlight(@PathVariable Integer id) {
        return flightService.cancelFlight(id);
    }

    @GetMapping("/get-by-flight-departure-date")
    List<GetFlightResponse> getFlightsByDepartureDate(String date) {
        return flightService.getFlightsByDepartureDate(date);
    }

    @GetMapping("/get-by-flight-destination-date")
    public List<GetFlightResponse> getFlightsByDestinationDate(String date) {
        return flightService.getFlightsByDestinationDate(date);
    }
}