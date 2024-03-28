package org.example.airplanemanegementsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.airplanemanegementsystem.request.personnel.CreatePersonnelRequest;
import org.example.airplanemanegementsystem.request.personnel.UpdatePersonnelRequest;
import org.example.airplanemanegementsystem.response.personnel.GetPersonnelResponse;
import org.example.airplanemanegementsystem.service.PersonnelService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/personnel")
@RequiredArgsConstructor
public class PersonnelController {
    private final PersonnelService personnelService;

    @GetMapping("/{id}")
    public GetPersonnelResponse getById(@PathVariable Integer id) {
        return personnelService.getById(id);
    }

    @GetMapping("get-all")
    public List<GetPersonnelResponse> getAll() {
        return personnelService.getAll();
    }

    @PostMapping
    public CreatePersonnelRequest add(@RequestBody @Valid CreatePersonnelRequest request) {
        return personnelService.add(request);
    }

    @DeleteMapping("/{id}")
    public GetPersonnelResponse deleteById(@PathVariable Integer id) {
        return personnelService.deleteById(id);
    }

    @PutMapping("/{id}")
    public GetPersonnelResponse update(@PathVariable Integer id, @Valid @RequestBody UpdatePersonnelRequest request) {
        return personnelService.update(id, request);
    }

    @GetMapping("/get-all-men")
    public List<GetPersonnelResponse> getAllMen() {
        return personnelService.getAllMen();
    }

    @GetMapping("/get-all-women")
    public List<GetPersonnelResponse> getAllWomen() {
        return personnelService.getAllWomen();
    }

    @GetMapping("/get-by-position")
    public List<GetPersonnelResponse> getByPosition(@RequestParam("position") @Valid String position) {
        return personnelService.getByPosition(position);
    }

    @GetMapping("/get-by-age/{age}")
    public List<GetPersonnelResponse> getByAge(@PathVariable Integer age) {
        return personnelService.getByAge(age);
    }

    @GetMapping("/get-by-hire-date-before")
    public List<GetPersonnelResponse> getByHireDateBefore(@RequestParam("date") @Valid String date) {
        return personnelService.getByHireDateBefore(date);
    }

    @GetMapping("/get-by-hire-date-after")
    public List<GetPersonnelResponse> getByHireDateAfter(@RequestParam("date") @Valid String date) {
        return personnelService.getByHireDateAfter(date);
    }

    @GetMapping("/get-by-salary-greater-than")
    public List<GetPersonnelResponse> getBySalaryGreaterThan(@RequestParam("salary") @Valid Double salary) {
        return personnelService.getBySalaryGreaterThan(salary);
    }

    @GetMapping("/get-by-salary-less-than")

    public List<GetPersonnelResponse> getBySalaryLessThan(@RequestParam("salary") @Valid Double salary) {
        return personnelService.getBySalaryLessThan(salary);
    }

    @GetMapping("/get-by-flight-id/{flightId}")
    public List<GetPersonnelResponse> getByFlightId(@PathVariable Integer flightId) {
        return personnelService.getByFlightId(flightId);
    }

    @GetMapping("/get-by-first-name")
    public List<GetPersonnelResponse> getByFirstName(@RequestParam("firstName") @Valid String firstName) {
        return personnelService.getByFirstName(firstName);
    }

    @GetMapping("/get-by-last-name")
    public List<GetPersonnelResponse> getByLastName(@RequestParam("lastName") @Valid String lastName) {
        return personnelService.getByLastName(lastName);
    }

    @GetMapping("/get-by-full-name")
    public List<GetPersonnelResponse> getByFullName(@RequestParam("fullName") @Valid String fullName) {
        return personnelService.getByFullName(fullName);
    }

    @GetMapping("/get-by-gender")
    public List<GetPersonnelResponse> getByGender(@RequestParam("gender") @Valid String gender) {
        return personnelService.getByGender(gender);
    }

    @GetMapping("/get-by-birthdate")
    public List<GetPersonnelResponse> getByBirthdate(@RequestParam("birthdate") @Valid String birthDate) {
        return personnelService.getByBirthdate(birthDate);
    }
}