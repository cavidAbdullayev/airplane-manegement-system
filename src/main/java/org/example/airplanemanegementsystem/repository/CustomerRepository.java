package org.example.airplanemanegementsystem.repository;



import lombok.NonNull;
import org.example.airplanemanegementsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> getCustomerByFirstName(String firstName);

    boolean existsByEmail(String email);

    boolean existsById(@NonNull Integer id);

    boolean existsByFIN(String fin);

    boolean existsByPhoneNumber(String phoneNumber);

    List<Customer> getCustomerByLastName(String lastName);

    Optional<Customer> getCustomerByEmail(String email);

    Optional<Customer> getCustomerByPhoneNumber(String phoneNumber);

    List<Customer> findByBirthdateBetween(LocalDate before, LocalDate after);

    Integer countByGender(Byte gender);

    List<Customer> findCustomersByAddressContainingIgnoreCase(String address);

    Optional<Customer> getCustomerByFIN(String fin);

    List<Customer> getCustomersByBirthdateBefore(LocalDate date);

    List<Customer> getCustomersByBirthdateAfter(LocalDate date);

    void deleteByPhoneNumber(String phoneNumber);
}