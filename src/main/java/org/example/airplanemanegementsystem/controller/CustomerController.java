package org.example.airplanemanegementsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.airplanemanegementsystem.request.customer.CreateCustomerRequest;
import org.example.airplanemanegementsystem.request.customer.UpdateCustomerRequest;
import org.example.airplanemanegementsystem.response.customer.GetCustomerResponse;
import org.example.airplanemanegementsystem.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public GetCustomerResponse getById(@PathVariable Integer id) {
        return customerService.getById(id);
    }

    @GetMapping("/get-all")
    public List<GetCustomerResponse> getAll() {
        return customerService.getAll();
    }

    @PostMapping
    public CreateCustomerRequest add(@Valid @RequestBody CreateCustomerRequest request) {
        return customerService.add(request);
    }

    @PutMapping("/{id}")
    public GetCustomerResponse update(@PathVariable Integer id, @Valid @RequestBody UpdateCustomerRequest request) {
        return customerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public GetCustomerResponse deleteById(@PathVariable Integer id) {
        return customerService.deleteById(id);
    }

    @GetMapping("/get-by-first-name")
    public List<GetCustomerResponse> getByFirstName(@RequestParam("firstName") String firstName) {
        return customerService.getByFirstName(firstName);
    }

    @GetMapping("/get-by-last-name")
    public List<GetCustomerResponse> getByLastName(@RequestParam("lastName") String lastName) {
        return customerService.getByLastName(lastName);
    }

    @GetMapping("/get-by-email")
    public GetCustomerResponse getByEmail(@RequestParam("email") String email) {
        return customerService.getByEmail(email);
    }

    @GetMapping("/get-by-phone-number")
    public GetCustomerResponse getByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber) {
        return customerService.getByPhoneNumber(phoneNumber);
    }

    @GetMapping("/get-by-birthdate-between")
    public List<GetCustomerResponse> getByBirthdateBetween(@RequestParam("before") String before,
                                                           @RequestParam("after") String after) {
        return customerService.getByBirthdateBetween(before, after);
    }

    @GetMapping("/count-by-gender/{gender}")
    public Integer countByGender(@PathVariable Byte gender) {
        return customerService.countByGender(gender);
    }

    @GetMapping("/get-by-address-containing")
    public List<GetCustomerResponse> getByAddressContaining(@RequestParam("address") String address) {
        return customerService.getByAddressContaining(address);
    }

    @GetMapping("/get-by-fin")
    public GetCustomerResponse getByFin(@RequestParam("fin") String fin) {
        return customerService.getByFin(fin);
    }

    @GetMapping("/get-by-birthdate-before")
    public List<GetCustomerResponse> getByBirthdateBefore(@RequestParam("date") String date) {
        return customerService.getByBirthdateBefore(date);
    }

    @GetMapping("/get-by-birthdate-after")
    public List<GetCustomerResponse> getByBirthdateAfter(@RequestParam("date") String date) {
        return customerService.getByBirthdateAfter(date);
    }

    @GetMapping("/get-by-age-greater-than/{age}")
    public List<GetCustomerResponse> getByAgeGreaterThanEqual(@PathVariable Integer age) {
        return customerService.getByAgeGreaterThanEqual(age);
    }

    @GetMapping("/get-by-age-less-than/{age}")
    public List<GetCustomerResponse> getByAgeLessThanEqual(@PathVariable Integer age) {
        return customerService.getByAgeLessThanEqual(age);
    }

    @GetMapping("/get-by-age-between")
    public List<GetCustomerResponse> getByAgeBetween(@RequestParam("before") Integer before, @RequestParam("after") Integer after) {
        return customerService.getByAgeBetween(before, after);
    }

    @DeleteMapping("/delete-by-phone-number")
    public GetCustomerResponse deleteByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber) {
        return customerService.deleteByPhoneNumber(phoneNumber);
    }
}