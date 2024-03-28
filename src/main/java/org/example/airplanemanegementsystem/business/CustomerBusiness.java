package org.example.airplanemanegementsystem.business;

import lombok.RequiredArgsConstructor;
import org.example.airplanemanegementsystem.enums.CustomerEnum;
import org.example.airplanemanegementsystem.exceptions.GeneralException;
import org.example.airplanemanegementsystem.model.Customer;
import org.example.airplanemanegementsystem.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CustomerBusiness {
 private final CustomerRepository customerRepository;

 public Long getCustomerAge(Customer customer) {
  return ChronoUnit.YEARS.between(customer.getBirthdate(), LocalDate.now());
 }

 public void checkExistsById(Integer id) {
  if (customerRepository.existsById(id))
   throw new GeneralException(CustomerEnum.CUSTOMER_NOT_EXISTS_BY_ID);
 }

 public void checkExistsByEmail(String email) {
  if (customerRepository.existsByEmail(email)) {
   throw new GeneralException(CustomerEnum.CUSTOMER_EXISTS_BY_EMAIL);
  }
 }

 public void checkNotExistsByEmail(String email) {
  if (!customerRepository.existsByEmail(email)) {
   throw new GeneralException(CustomerEnum.CUSTOMER_NOT_EXISTS_BY_EMAIL);
  }
 }

 public void checkExistsByFin(String fin) {
  if (customerRepository.existsByFIN(fin))
   throw new GeneralException(CustomerEnum.CUSTOMER_EXISTS_BY_FIN);
 }

 public void checkExistsByPhoneNumber(String phoneNumber) {
  if (customerRepository.existsByPhoneNumber(phoneNumber))
   throw new GeneralException(CustomerEnum.CUSTOMER_EXISTS_BY_PHONE_NUMBER);
 }
}