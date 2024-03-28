package org.example.airplanemanegementsystem.service;

import org.example.airplanemanegementsystem.request.personnel.CreatePersonnelRequest;
import org.example.airplanemanegementsystem.request.personnel.UpdatePersonnelRequest;
import org.example.airplanemanegementsystem.response.personnel.GetPersonnelResponse;

import java.util.List;

public interface PersonnelService {
    GetPersonnelResponse getById(Integer id);

    List<GetPersonnelResponse> getAll();

    CreatePersonnelRequest add(CreatePersonnelRequest request);

    GetPersonnelResponse deleteById(Integer id);

    GetPersonnelResponse update(Integer id, UpdatePersonnelRequest request);

    List<GetPersonnelResponse> getAllMen();

    List<GetPersonnelResponse> getAllWomen();

    List<GetPersonnelResponse> getByPosition(String position);

    List<GetPersonnelResponse> getByAge(Integer age);

    List<GetPersonnelResponse> getByHireDateBefore(String date);

    List<GetPersonnelResponse> getByHireDateAfter(String date);

    List<GetPersonnelResponse> getBySalaryGreaterThan(Double salary);

    List<GetPersonnelResponse> getBySalaryLessThan(Double salary);

    List<GetPersonnelResponse> getByFlightId(Integer flightId);

    List<GetPersonnelResponse> getByFirstName(String firstName);

    List<GetPersonnelResponse> getByLastName(String lastName);

    List<GetPersonnelResponse> getByFullName(String fullName);

    List<GetPersonnelResponse> getByGender(String gender);

    List<GetPersonnelResponse> getByBirthdate(String birthDate);
}