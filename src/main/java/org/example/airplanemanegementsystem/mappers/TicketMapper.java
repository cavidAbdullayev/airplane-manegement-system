package org.example.airplanemanegementsystem.mappers;

import org.example.airplanemanegementsystem.model.Ticket;
import org.example.airplanemanegementsystem.request.ticket.CreateTicketRequest;
import org.example.airplanemanegementsystem.request.ticket.UpdateTicketRequest;
import org.example.airplanemanegementsystem.response.ticket.GetTicketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TicketMapper {
    @Mapping(target = "flightId", source = "flight.id")
    @Mapping(target = "customerId", source = "customer.id")
    GetTicketResponse mapToGetTicketResponse(Ticket ticket);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerFullName", ignore = true)
    @Mapping(target = "purchaseDate", ignore = true)
    @Mapping(target = "price", ignore = true)
    Ticket mapToTicketFromCreateTicketRequest(CreateTicketRequest request);

    void mapToTicketFromUpdateTicketRequest(@MappingTarget Ticket ticket, UpdateTicketRequest request);
}