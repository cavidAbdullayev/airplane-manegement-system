package org.example.airplanemanegementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.airplanemanegementsystem.business.CustomerBusiness;
import org.example.airplanemanegementsystem.enums.CustomerEnum;
import org.example.airplanemanegementsystem.exceptions.GeneralException;
import org.example.airplanemanegementsystem.mappers.CustomerMapper;
import org.example.airplanemanegementsystem.model.Customer;
import org.example.airplanemanegementsystem.repository.CustomerRepository;
import org.example.airplanemanegementsystem.request.customer.CreateCustomerRequest;
import org.example.airplanemanegementsystem.request.customer.UpdateCustomerRequest;
import org.example.airplanemanegementsystem.response.customer.GetCustomerResponse;
import org.example.airplanemanegementsystem.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final CustomerBusiness customerBusiness;

    @Override
    public GetCustomerResponse getById(Integer id) {
        return customerMapper.mapToGetCustomerResponse(customerRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException(CustomerEnum.CUSTOMER_NOT_EXISTS_BY_ID)));
    }

    @Override
    public List<GetCustomerResponse> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::mapToGetCustomerResponse)
                .toList();
    }

    @Override
    public CreateCustomerRequest add(CreateCustomerRequest request) {
        Customer customer = customerMapper.mapToCustomerFromCreateCustomerRequest(request);
        customer.setBirthdate(LocalDate.parse(request
                        .getBirthdate(),
                DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        customerBusiness.checkExistsByEmail(customer.getEmail());
        customerRepository.save(customer);
        return request;
    }

    @Transactional
    @Override
    public GetCustomerResponse update(Integer id, UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new GeneralException(CustomerEnum.CUSTOMER_NOT_EXISTS_BY_ID));
        customerMapper.mapForUpdate(customer, updateCustomerRequest);
        if (updateCustomerRequest.getBirthdate() != null)
            customer.setBirthdate(LocalDate.parse(updateCustomerRequest.getBirthdate(),
                    DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        if (updateCustomerRequest.getEmail() != null)
            customerBusiness.checkExistsByEmail(updateCustomerRequest.getEmail());
        if (updateCustomerRequest.getFIN() != null)
            customerBusiness.checkExistsByFin(updateCustomerRequest.getFIN());
        if (updateCustomerRequest.getPhoneNumber() != null)
            customerBusiness.checkExistsByPhoneNumber(updateCustomerRequest.getPhoneNumber());
        customerRepository.save(customer);
        return customerMapper.mapToGetCustomerResponse(customer);
    }

    @Transactional
    @Override
    public GetCustomerResponse deleteById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new GeneralException(CustomerEnum.CUSTOMER_NOT_EXISTS_BY_ID));
        GetCustomerResponse response = customerMapper.mapToGetCustomerResponse(customer);
        customerRepository.deleteById(id);
        return response;
    }

    @Override
    public List<GetCustomerResponse> getByFirstName(String firstName) {
        return customerRepository.getCustomerByFirstName(firstName)
                .stream()
                .map(customerMapper::mapToGetCustomerResponse)
                .toList();
    }

    @Override
    public List<GetCustomerResponse> getByLastName(String lastName) {
        return customerRepository.getCustomerByLastName(lastName)
                .stream()
                .map(customerMapper::mapToGetCustomerResponse)
                .toList();
    }

    @Override
    public GetCustomerResponse getByEmail(String email) {
        return customerMapper.mapToGetCustomerResponse(customerRepository
                .getCustomerByEmail(email)
                .orElseThrow(() ->
                        new GeneralException(CustomerEnum.CUSTOMER_NOT_EXISTS_BY_EMAIL)));
    }

    @Override
    public GetCustomerResponse getByPhoneNumber(String phoneNumber) {
        return customerMapper.mapToGetCustomerResponse(customerRepository
                .getCustomerByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new GeneralException(CustomerEnum.CUSTOMER_NOT_EXISTS_BY_PHONE_NUMBER)));
    }

    @Override
    public List<GetCustomerResponse> getByBirthdateBetween(String before, String after) {
        return customerRepository.findByBirthdateBetween(
                        LocalDate.parse(before, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                        LocalDate.parse(after, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .stream()
                .map(customerMapper::mapToGetCustomerResponse)
                .toList();
    }

    @Override
    public Integer countByGender(Byte gender) {
        if (gender != 1 && gender != 0)
            throw new GeneralException(CustomerEnum.INVALID_GENDER);
        return customerRepository.countByGender(gender);
    }

    @Override
    public List<GetCustomerResponse> getByAddressContaining(String address) {
        return customerRepository.findCustomersByAddressContainingIgnoreCase(address)
                .stream()
                .map(customerMapper::mapToGetCustomerResponse)
                .toList();
    }

    @Override
    public GetCustomerResponse getByFin(String fin) {
        return customerMapper.mapToGetCustomerResponse(customerRepository
                .getCustomerByFIN(fin)
                .orElseThrow(() ->
                        new GeneralException(CustomerEnum.CUSTOMER_NOT_EXISTS_BY_FIN)));
    }

    @Override
    public List<GetCustomerResponse> getByBirthdateBefore(String date) {
        return customerRepository
                .getCustomersByBirthdateBefore(LocalDate
                        .parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .stream()
                .map(customerMapper::mapToGetCustomerResponse)
                .toList();
    }

    @Override
    public List<GetCustomerResponse> getByBirthdateAfter(String date) {
        return customerRepository.getCustomersByBirthdateAfter(LocalDate
                        .parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .stream()
                .map(customerMapper::mapToGetCustomerResponse)
                .toList();
    }

    @Override
    public List<GetCustomerResponse> getByAgeGreaterThanEqual(Integer age) {
        return customerRepository.findAll()
                .stream().
                filter(customer -> customerBusiness.getCustomerAge(customer) >= age)
                .map(customerMapper::mapToGetCustomerResponse)
                .toList();
    }

    @Override
    public List<GetCustomerResponse> getByAgeLessThanEqual(Integer age) {
        return customerRepository.findAll()
                .stream().
                filter(customer -> customerBusiness.getCustomerAge(customer) <= age)
                .map(customerMapper::mapToGetCustomerResponse)
                .toList();
    }

    @Override
    public List<GetCustomerResponse> getByAgeBetween(Integer before, Integer after) {
        return customerRepository.findAll()
                .stream()
                .filter(customer -> customerBusiness.getCustomerAge(customer) >= after
                        && customerBusiness.getCustomerAge(customer) < before)
                .map(customerMapper::mapToGetCustomerResponse)
                .toList();
    }

    @Override
    @Transactional
    public GetCustomerResponse deleteByPhoneNumber(String phoneNumber) {
        GetCustomerResponse response = customerMapper
                .mapToGetCustomerResponse(customerRepository.
                        getCustomerByPhoneNumber(phoneNumber)
                        .orElseThrow(() ->
                                new GeneralException(CustomerEnum.CUSTOMER_NOT_EXISTS_BY_PHONE_NUMBER)));
        customerRepository.deleteByPhoneNumber(phoneNumber);
        return response;
    }
}