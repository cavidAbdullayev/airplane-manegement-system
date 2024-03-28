package org.example.airplanemanegementsystem.service;

import org.example.airplanemanegementsystem.model.Flight;
import org.example.airplanemanegementsystem.request.aircraft.CreateAircraftRequest;
import org.example.airplanemanegementsystem.request.aircraft.UpdateAircraftRequest;
import org.example.airplanemanegementsystem.request.personnel.CreatePersonnelRequest;
import org.example.airplanemanegementsystem.response.aircraft.GetAircraftResponse;
import org.example.airplanemanegementsystem.response.flight.GetFlightResponse;

import java.time.LocalDate;
import java.util.List;

public interface AircraftService {

    GetAircraftResponse add(CreateAircraftRequest request);

    GetAircraftResponse getById(Integer id);

    List<GetAircraftResponse> getAll();

    GetAircraftResponse deleteById(Integer id);

    GetAircraftResponse update(Integer id, UpdateAircraftRequest request);

    List<GetAircraftResponse> getAircraftByModel(String model);

    List<GetAircraftResponse> getAircraftByProductionDate(String date);

    List<GetAircraftResponse> getAircraftByMaintenanceStatus(String status);

    List<GetAircraftResponse> getAircraftByAvailabilityStatus(String status);

    List<GetAircraftResponse> getAircraftByType(String type);

    List<GetAircraftResponse> getAircraftBySeatQuantity(Short seatQuantity);

    List<GetFlightResponse> getFlightsOfAircraft(Integer aircraftId);

    GetAircraftResponse updateProductionDate(Integer id, String productionDate);

    GetAircraftResponse updateAvailabilityStatus(Integer id, String availabilityStatus);

    GetAircraftResponse updateMaintenanceStatus(Integer id, String maintenanceStatus);

    List<GetAircraftResponse> getAircraftProducedBetween(String startDate, String endDate);
}