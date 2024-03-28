package org.example.airplanemanegementsystem.mappers;

import org.example.airplanemanegementsystem.model.Aircraft;
import org.example.airplanemanegementsystem.request.aircraft.CreateAircraftRequest;
import org.example.airplanemanegementsystem.request.aircraft.UpdateAircraftRequest;
import org.example.airplanemanegementsystem.response.aircraft.GetAircraftResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AircraftMapper {

    GetAircraftResponse mapToGetAircraftResponse(Aircraft aircraft);

    @Mapping(target = "id", ignore = true)
    Aircraft mapToAircraftFromCreateAircraftRequest(CreateAircraftRequest request);

    @Mapping(target = "id", ignore = true)
    void updateAircraftFromRequest(UpdateAircraftRequest request, @MappingTarget Aircraft aircraft);
}