package org.example.airplanemanegementsystem.mappers;

import org.example.airplanemanegementsystem.model.Flight;
import org.example.airplanemanegementsystem.request.flight.CreateFlightRequest;
import org.example.airplanemanegementsystem.request.flight.UpdateFlightRequest;
import org.example.airplanemanegementsystem.response.flight.GetFlightResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FlightMapper {
    @Mapping(target = "aircraftId", source = "aircraft.id")
    GetFlightResponse mapToGetFlightResponse(Flight flight);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "departureDate", ignore = true)
    @Mapping(target = "destinationDate", ignore = true)
    @Mapping(target = "aircraft.id", source = "aircraftId")
    Flight mapToFlightFromCreateFlightRequest(CreateFlightRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "departureDate", ignore = true)
    @Mapping(target = "destinationDate", ignore = true)
    void mapForUpdate(@MappingTarget Flight flight, UpdateFlightRequest request);
}