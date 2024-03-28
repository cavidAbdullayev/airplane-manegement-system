package org.example.airplanemanegementsystem.mappers;

import org.example.airplanemanegementsystem.model.Personnel;
import org.example.airplanemanegementsystem.request.customer.UpdateCustomerRequest;
import org.example.airplanemanegementsystem.request.personnel.CreatePersonnelRequest;
import org.example.airplanemanegementsystem.request.personnel.UpdatePersonnelRequest;
import org.example.airplanemanegementsystem.response.personnel.GetPersonnelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PersonnelMapper {
    GetPersonnelResponse mapToGetPersonnelResponse(Personnel personnel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "birthdate", ignore = true)
    @Mapping(target = "hireDate", ignore = true)
    @Mapping(source = "flightId", target = "flight.id")
    Personnel mapToPersonnelFromCreatePersonnelRequest(CreatePersonnelRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "birthdate", ignore = true)
    @Mapping(target = "hireDate", ignore = true)
    @Mapping(target = "flight.id", source = "flightId")
    void mapForUpdate(@MappingTarget Personnel personnel, UpdatePersonnelRequest request);
}