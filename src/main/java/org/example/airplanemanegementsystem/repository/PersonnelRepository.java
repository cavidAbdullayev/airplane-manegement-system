package org.example.airplanemanegementsystem.repository;

import org.example.airplanemanegementsystem.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel,Integer> {
    List<Personnel> findAllByGender(Byte gender);

    List<Personnel> findByPosition(String position);

    List<Personnel> findByBirthdate(LocalDate date);

    List<Personnel> findByHireDateBefore(LocalDate date);

    List<Personnel> findByHireDateAfter(LocalDate date);

    List<Personnel> findBySalaryGreaterThan(Double salary);

    List<Personnel> findBySalaryLessThan(Double salary);

    List<Personnel> findByFlight_Id(Integer flightId);

    List<Personnel> findByFirstName(String firstName);

    List<Personnel> findByLastName(String lastName);

    List<Personnel> findAllByLastNameAndFirstName(String lastName, String firstName);
}