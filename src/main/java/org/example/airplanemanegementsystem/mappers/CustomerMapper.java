package org.example.airplanemanegementsystem.mappers;

import org.example.airplanemanegementsystem.model.Customer;
import org.example.airplanemanegementsystem.request.customer.CreateCustomerRequest;
import org.example.airplanemanegementsystem.request.customer.UpdateCustomerRequest;
import org.example.airplanemanegementsystem.response.customer.GetCustomerResponse;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy =
        NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    GetCustomerResponse mapToGetCustomerResponse(Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "birthdate", ignore = true)
    Customer mapToCustomerFromCreateCustomerRequest(CreateCustomerRequest createCustomerRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "birthdate", ignore = true)
    void mapForUpdate(@MappingTarget Customer customer, UpdateCustomerRequest request);
}