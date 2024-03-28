package org.example.airplanemanegementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.airplanemanegementsystem.enums.AircraftEnum;
import org.example.airplanemanegementsystem.exceptions.GeneralException;
import org.example.airplanemanegementsystem.mappers.AircraftMapper;
import org.example.airplanemanegementsystem.mappers.FlightMapper;
import org.example.airplanemanegementsystem.model.Aircraft;
import org.example.airplanemanegementsystem.repository.AircraftRepository;
import org.example.airplanemanegementsystem.repository.FlightRepository;
import org.example.airplanemanegementsystem.request.aircraft.CreateAircraftRequest;
import org.example.airplanemanegementsystem.request.aircraft.UpdateAircraftRequest;
import org.example.airplanemanegementsystem.response.aircraft.GetAircraftResponse;
import org.example.airplanemanegementsystem.response.flight.GetFlightResponse;
import org.example.airplanemanegementsystem.service.AircraftService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AircraftServiceImpl implements AircraftService {
    private final AircraftRepository aircraftRepository;
    private final AircraftMapper aircraftMapper;
    private final FlightMapper flightMapper;
    private final FlightRepository flightRepository;

    @Override
    public GetAircraftResponse add(CreateAircraftRequest request) {
        Aircraft aircraft = aircraftMapper
                .mapToAircraftFromCreateAircraftRequest(request);
        aircraftRepository.save(aircraft);
        return aircraftMapper.mapToGetAircraftResponse(aircraft);
    }

    @Override
    public GetAircraftResponse getById(Integer id) {
        return aircraftMapper.mapToGetAircraftResponse(aircraftRepository
                .findById(id)
                .orElseThrow(()
                        -> new GeneralException(AircraftEnum.AIRCRAFT_NOT_EXISTS)));
    }

    @Override
    public List<GetAircraftResponse> getAll() {
        return aircraftRepository.findAll()
                .stream()
                .map(aircraftMapper::mapToGetAircraftResponse)
                .toList();
    }

    @Override
    @Transactional
    public GetAircraftResponse deleteById(Integer id) {
        Aircraft aircraft = aircraftRepository
                .findById(id)
                .orElseThrow(()
                        -> new GeneralException(AircraftEnum.AIRCRAFT_NOT_EXISTS));
        aircraftRepository.deleteById(id);
        return aircraftMapper.mapToGetAircraftResponse(aircraft);
    }

    @Override
    @Transactional
    public GetAircraftResponse update(Integer id, UpdateAircraftRequest request) {
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new GeneralException(AircraftEnum.AIRCRAFT_NOT_EXISTS));
        aircraftMapper.updateAircraftFromRequest(request, aircraft);
        aircraftRepository.save(aircraft);
        return aircraftMapper.mapToGetAircraftResponse(aircraft);
    }

    @Override
    public List<GetAircraftResponse> getAircraftByModel(String model) {
        return aircraftRepository.findAircraftsByAircraftModel(model)
                .stream()
                .map(aircraftMapper::mapToGetAircraftResponse)
                .toList();
    }

    @Override
    public List<GetAircraftResponse> getAircraftByProductionDate(String date) {
        return aircraftRepository.findAircraftsByProductionDate(LocalDate
                        .parse(date, DateTimeFormatter
                                .ofPattern("dd.MM.yyyy")))
                .stream()
                .map(aircraftMapper::mapToGetAircraftResponse)
                .toList();
    }

    @Override
    public List<GetAircraftResponse> getAircraftByMaintenanceStatus(String status) {
        return aircraftRepository.findAircraftsByMaintenanceStatus(status)
                .stream()
                .map(aircraftMapper::mapToGetAircraftResponse)
                .toList();
    }

    @Override
    public List<GetAircraftResponse> getAircraftByAvailabilityStatus(String status) {
        return aircraftRepository.findAircraftsByAvailabilityStatus(status)
                .stream()
                .map(aircraftMapper::mapToGetAircraftResponse)
                .toList();
    }

    @Override
    public List<GetAircraftResponse> getAircraftByType(String type) {
        return aircraftRepository.findAircraftsByAircraftType(type)
                .stream()
                .map(aircraftMapper::mapToGetAircraftResponse)
                .toList();
    }

    @Override
    public List<GetAircraftResponse> getAircraftBySeatQuantity(Short seatQuantity) {
        return aircraftRepository.findAircraftsBySeatQuantity(seatQuantity)
                .stream()
                .map(aircraftMapper::mapToGetAircraftResponse)
                .toList();
    }

    @Override
    public List<GetFlightResponse> getFlightsOfAircraft(Integer aircraftId) {
        Aircraft aircraft = aircraftRepository.findById(aircraftId)
                .orElseThrow(() ->
                        new GeneralException(AircraftEnum.AIRCRAFT_NOT_EXISTS));
        return flightRepository.findFlightsByAircraftId(aircraft.getId())
                .stream()
                .map(flightMapper::mapToGetFlightResponse)
                .toList();
    }

    @Override
    public GetAircraftResponse updateProductionDate(Integer id, String productionDate) {
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() ->
                        new GeneralException(AircraftEnum.AIRCRAFT_NOT_EXISTS));
        aircraft.setProductionDate(LocalDate.parse(productionDate,
                DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        aircraftRepository.save(aircraft);
        return aircraftMapper.mapToGetAircraftResponse(aircraft);
    }

    @Override
    public GetAircraftResponse updateAvailabilityStatus(Integer id, String availabilityStatus) {
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() ->
                        new GeneralException(AircraftEnum.AIRCRAFT_NOT_EXISTS));
        aircraft.setAvailabilityStatus(availabilityStatus);
        aircraftRepository.save(aircraft);
        return aircraftMapper.mapToGetAircraftResponse(aircraft);
    }

    @Override
    public GetAircraftResponse updateMaintenanceStatus(Integer id, String maintenanceStatus) {
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() ->
                        new GeneralException(AircraftEnum.AIRCRAFT_NOT_EXISTS));
        aircraft.setMaintenanceStatus(maintenanceStatus);
        aircraftRepository.save(aircraft);
        return aircraftMapper.mapToGetAircraftResponse(aircraft);
    }

    @Override
    public List<GetAircraftResponse> getAircraftProducedBetween(String startDate, String endDate) {
        return aircraftRepository.findAircraftsByProductionDateBetween(LocalDate
                                .parse(startDate,
                                        DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        LocalDate.parse(endDate,
                                DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .stream()
                .map(aircraftMapper::mapToGetAircraftResponse)
                .toList();
    }
}