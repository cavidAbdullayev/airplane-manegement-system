package org.example.airplanemanegementsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.airplanemanegementsystem.request.aircraft.CreateAircraftRequest;
import org.example.airplanemanegementsystem.request.aircraft.UpdateAircraftRequest;
import org.example.airplanemanegementsystem.response.aircraft.GetAircraftResponse;
import org.example.airplanemanegementsystem.response.flight.GetFlightResponse;
import org.example.airplanemanegementsystem.service.AircraftService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/aircraft")
public class AircraftController {
    private final AircraftService aircraftService;

    @GetMapping("/{id}")
    public GetAircraftResponse getById(@PathVariable Integer id) {
        return aircraftService.getById(id);
    }

    @GetMapping("/get-all")
    public List<GetAircraftResponse> getAll() {
        return aircraftService.getAll();
    }

    @PostMapping
    public GetAircraftResponse add(@Valid @RequestBody CreateAircraftRequest request) {
        return aircraftService.add(request);
    }

    @DeleteMapping("/{id}")
    public GetAircraftResponse delete(@PathVariable Integer id) {
        return aircraftService.deleteById(id);
    }

    @PutMapping("/{id}")
    public GetAircraftResponse update(@PathVariable Integer id,
                                      @Valid @RequestBody UpdateAircraftRequest request) {
        return aircraftService.update(id, request);
    }

    @GetMapping("/get-by-model")
    public List<GetAircraftResponse> getAircraftByModel(@RequestParam("model") String model) {
        return aircraftService.getAircraftByModel(model);
    }

    @GetMapping("/get-by-production-date")
    public List<GetAircraftResponse> getAircraftByProductionDate(@RequestParam("date") String date) {
        return aircraftService.getAircraftByProductionDate(date);
    }

    @GetMapping("/get-by-maintenance-status")
    public List<GetAircraftResponse> getAircraftByMaintenanceStatus(@RequestParam("status") String status) {
        return aircraftService.getAircraftByMaintenanceStatus(status);
    }

    @GetMapping("/get-by-availability-status")
    public List<GetAircraftResponse> getAircraftByAvailabilityStatus(@RequestParam("status") String status) {
        return aircraftService.getAircraftByAvailabilityStatus(status);
    }

    @GetMapping("/get-by-type")
    public List<GetAircraftResponse> getAircraftByType(@RequestParam("type") String type) {
        return aircraftService.getAircraftByType(type);
    }

    @GetMapping("/get-by-seat-quantity/{quantity}")
    public List<GetAircraftResponse> getAircraftBySeatQuantity(@PathVariable Short quantity) {
        return aircraftService.getAircraftBySeatQuantity(quantity);
    }

    @GetMapping("/get-flights/{aircraftId}")
    public List<GetFlightResponse> getFlightsOfAircraft(@PathVariable Integer aircraftId) {
        return aircraftService.getFlightsOfAircraft(aircraftId);
    }

    @PutMapping("/update-production-date/{id}")
    public GetAircraftResponse updateProductionDate(@PathVariable Integer id,
                                                    @RequestParam("date") String date) {
        return aircraftService.updateProductionDate(id, date);
    }

    @PutMapping("/update-availability-status/{id}")
    public GetAircraftResponse updateAvailabilityStatus(@PathVariable Integer id,
                                                        @RequestParam("status") String status) {
        return aircraftService.updateAvailabilityStatus(id, status);
    }

    @PutMapping("/update-maintenance-status/{id}")
    public GetAircraftResponse updateMaintenanceStatus(@PathVariable Integer id,
                                                       @RequestParam("status") String status) {
        return aircraftService.updateMaintenanceStatus(id, status);
    }

    @GetMapping("/get-by-production-date-between")
    public List<GetAircraftResponse> getAircraftProducedBetween(@RequestParam("start") String start,
                                                                @RequestParam("end") String end) {
        return aircraftService.getAircraftProducedBetween(start, end);
    }
}