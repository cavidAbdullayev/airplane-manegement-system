package org.example.airplanemanegementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.airplanemanegementsystem.business.FlightBusiness;
import org.example.airplanemanegementsystem.enums.AircraftEnum;
import org.example.airplanemanegementsystem.enums.FlightEnum;
import org.example.airplanemanegementsystem.exceptions.GeneralException;
import org.example.airplanemanegementsystem.mappers.FlightMapper;
import org.example.airplanemanegementsystem.model.Flight;
import org.example.airplanemanegementsystem.repository.AircraftRepository;
import org.example.airplanemanegementsystem.repository.FlightRepository;
import org.example.airplanemanegementsystem.request.flight.CreateFlightRequest;
import org.example.airplanemanegementsystem.request.flight.UpdateFlightRequest;
import org.example.airplanemanegementsystem.response.flight.GetFlightResponse;
import org.example.airplanemanegementsystem.service.FlightService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final AircraftRepository aircraftRepository;
    private final FlightBusiness flightBusiness;

    @Override
    public CreateFlightRequest add(CreateFlightRequest request) {
        if (aircraftRepository.findById(request.getAircraftId()).isEmpty())
            throw new GeneralException(AircraftEnum.AIRCRAFT_NOT_EXISTS);
        if (!aircraftRepository.findById(request.getAircraftId()).get().getAvailabilityStatus().equals("Available"))
            throw new GeneralException(AircraftEnum.AIRCRAFT_NOT_AVAILABLE);
        Flight flight = flightMapper
                .mapToFlightFromCreateFlightRequest(request);
        flight.setDepartureDate(LocalDateTime
                .parse(request.getDepartureTime(),
                        DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
                .withNano(0));
        flight.setDestinationDate(LocalDateTime
                .parse(request.getDestinationTime(),
                        DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
                .withNano(0));
        flightRepository.save(flight);
        return request;
    }

    @Override
    public GetFlightResponse getById(Integer id) {
        Flight flight = flightRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException(FlightEnum.FLIGHT_NOT_EXISTS_BY_ID));
        flightBusiness.checkFlightAndThrow(flight);
        return flightMapper.mapToGetFlightResponse(flight);
    }

    @Override
    public List<GetFlightResponse> getAll() {
        return flightRepository.findAll()
                .stream()
                .map(flightBusiness::checkFlight)
                .filter(flight -> !(flight.getIsEnded() || flight.getIsCancelled()))
                .map(flightMapper::mapToGetFlightResponse)
                .toList();
    }

    @Transactional
    @Override
    public GetFlightResponse update(Integer id, UpdateFlightRequest request) {
        Flight flight = flightRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Flight given by id-" +
                        id + " doesn't exists!"));
        flightBusiness.checkFlightAndThrow(flight);
        if (request.getAircraftId() != null) {
            if (aircraftRepository.findById(request.getAircraftId()).isEmpty())
                throw new GeneralException(AircraftEnum.AIRCRAFT_NOT_EXISTS);
            if (!aircraftRepository.findById(request.getAircraftId()).get().getAvailabilityStatus().equals("Available"))
                throw new GeneralException(AircraftEnum.AIRCRAFT_NOT_AVAILABLE);
        }
        flightMapper.mapForUpdate(flight, request);
        if (request.getDepartureTime() != null) {
            flight.setDepartureDate(LocalDateTime
                    .parse(request.getDepartureTime(),
                            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
                    .withNano(0));
        }
        if (request.getDestinationTime() != null) {
            flight.setDestinationDate(LocalDateTime
                    .parse(request.getDestinationTime(),
                            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
                    .withNano(0));
        }
        flightRepository.save(flight);
        return flightMapper.mapToGetFlightResponse(flight);
    }

    @Override
    @Transactional
    public GetFlightResponse cancel(Integer id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() ->
                        new GeneralException(FlightEnum.FLIGHT_NOT_EXISTS_BY_ID));
        flight.setIsCancelled(true);
        flightRepository.save(flight);
        return flightMapper.mapToGetFlightResponse(flight);
    }

    @Override
    public List<GetFlightResponse> getFlightsByDeparturePoint(String point) {
        return flightRepository.findFlightsByDeparturePoint(point)
                .stream()
                .map(flightBusiness::checkFlight)
                .filter(flight -> !(flight.getIsCancelled() || flight.getIsEnded())
                        && flight.getDeparturePoint().equals(point))
                .map(flightMapper::mapToGetFlightResponse)
                .toList();
    }

    @Override
    public List<GetFlightResponse> getFlightsByDestinationPoint(String point) {
        return flightRepository.findFlightsByDestinationPoint(point)
                .stream()
                .map(flightBusiness::checkFlight)
                .filter(flight -> !(flight.getIsEnded() || flight.getIsCancelled()))
                .map(flightMapper::mapToGetFlightResponse)
                .toList();
    }

    @Override
    public List<GetFlightResponse> getFlightsBetween(String before, String after) {
        return flightRepository.findFlightsByDepartureDateBetween(
                        LocalDateTime.parse(before, DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss")),
                        LocalDateTime.parse(after, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")))
                .stream()
                .filter(flight -> !flight.getIsCancelled())
                .map(flightMapper::mapToGetFlightResponse)
                .toList();
    }

    @Override
    public List<GetFlightResponse> getFlightsByPriceBetween(Double min, Double max) {
        return flightRepository.findFlightsByPriceBetween(min, max)
                .stream()
                .map(flightBusiness::checkFlight)
                .filter(flight -> !(flight.getIsCancelled()
                        || flight.getIsEnded()))
                .map(flightMapper::mapToGetFlightResponse)
                .toList();
    }

    @Override
    public List<GetFlightResponse> getFlightsByAircraftType(String type) {
        return flightRepository.findFlightsByAircraft_AircraftType(type)
                .stream()
                .map(flightBusiness::checkFlight)
                .filter(flight -> !flight.getIsCancelled())
                .map(flightMapper::mapToGetFlightResponse)
                .toList();
    }

    @Override
    @Transactional
    public GetFlightResponse markFlightAsComplete(Integer id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() ->
                        new GeneralException(FlightEnum.FLIGHT_NOT_EXISTS_BY_ID));
        if (flight.getIsCancelled())
            throw new GeneralException(FlightEnum.FLIGHT_CANCELLED);
        if (flight.getIsEnded())
            throw new GeneralException(FlightEnum.FLIGHT_ENDED);
        if (!(Duration.between(LocalDateTime.now(), flight.getDepartureDate()).toSeconds() < 0))
            throw new GeneralException(FlightEnum.FLIGHT_NOT_START);
        flight.setIsEnded(true);
        flightRepository.save(flight);
        return flightMapper.mapToGetFlightResponse(flight);
    }

    @Override
    @Transactional
    public GetFlightResponse cancelFlight(Integer id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() ->
                        new GeneralException(FlightEnum.FLIGHT_NOT_EXISTS_BY_ID));
        if (flight.getIsCancelled())
            throw new GeneralException(FlightEnum.FLIGHT_CANCELLED);
        if (flight.getIsEnded())
            throw new GeneralException(FlightEnum.FLIGHT_ENDED);
        flight.setIsCancelled(true);
        flightRepository.save(flight);
        return flightMapper.mapToGetFlightResponse(flight);
    }

    @Override
    public List<GetFlightResponse> getFlightsByDepartureDate(String date) {
        return flightRepository.findFlightsByDepartureDate(
                        LocalDateTime.parse(date,
                                DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss")))
                .stream()
                .map(flightBusiness::checkFlight)
                .filter(flight -> !(flight.getIsEnded() || flight.getIsCancelled()))
                .map(flightMapper::mapToGetFlightResponse)
                .toList();
    }

    @Override
    public List<GetFlightResponse> getFlightsByDestinationDate(String date) {
        return flightRepository.findFlightsByDestinationDate(
                        LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss")))
                .stream()
                .map(flightBusiness::checkFlight)
                .filter(flight -> !(flight.getIsCancelled() || flight.getIsEnded()))
                .map(flightMapper::mapToGetFlightResponse)
                .toList();
    }
}