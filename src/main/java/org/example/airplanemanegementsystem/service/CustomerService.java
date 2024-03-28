package org.example.airplanemanegementsystem.service;

import org.example.airplanemanegementsystem.request.customer.CreateCustomerRequest;
import org.example.airplanemanegementsystem.request.customer.UpdateCustomerRequest;
import org.example.airplanemanegementsystem.response.customer.GetCustomerResponse;

import java.util.List;

public interface CustomerService {
    GetCustomerResponse getById(Integer id);

    List<GetCustomerResponse> getAll();

    CreateCustomerRequest add(CreateCustomerRequest request);

    GetCustomerResponse update(Integer id, UpdateCustomerRequest request);

    GetCustomerResponse deleteById(Integer id);

    List<GetCustomerResponse> getByFirstName(String firstName);

    List<GetCustomerResponse> getByLastName(String lastName);

    GetCustomerResponse getByEmail(String email);

    GetCustomerResponse getByPhoneNumber(String phoneNumber);

    List<GetCustomerResponse> getByBirthdateBetween(String before, String after);

    Integer countByGender(Byte gender);

    List<GetCustomerResponse> getByAddressContaining(String address);

    GetCustomerResponse getByFin(String fin);

    List<GetCustomerResponse> getByBirthdateBefore(String date);

    List<GetCustomerResponse> getByBirthdateAfter(String date);

    List<GetCustomerResponse> getByAgeGreaterThanEqual(Integer age);

    List<GetCustomerResponse> getByAgeLessThanEqual(Integer age);

    List<GetCustomerResponse> getByAgeBetween(Integer before, Integer after);

    GetCustomerResponse deleteByPhoneNumber(String phoneNumber);
}