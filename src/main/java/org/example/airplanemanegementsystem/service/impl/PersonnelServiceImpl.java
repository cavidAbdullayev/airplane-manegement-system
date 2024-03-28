package org.example.airplanemanegementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.airplanemanegementsystem.business.FlightBusiness;
import org.example.airplanemanegementsystem.enums.CustomerEnum;
import org.example.airplanemanegementsystem.enums.PersonnelEnum;
import org.example.airplanemanegementsystem.exceptions.GeneralException;
import org.example.airplanemanegementsystem.mappers.PersonnelMapper;
import org.example.airplanemanegementsystem.model.Personnel;
import org.example.airplanemanegementsystem.repository.PersonnelRepository;
import org.example.airplanemanegementsystem.request.personnel.CreatePersonnelRequest;
import org.example.airplanemanegementsystem.request.personnel.UpdatePersonnelRequest;
import org.example.airplanemanegementsystem.response.personnel.GetPersonnelResponse;
import org.example.airplanemanegementsystem.service.PersonnelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PersonnelServiceImpl implements PersonnelService {
    private final PersonnelRepository personnelRepository;
    private final PersonnelMapper personnelMapper;
    private final FlightBusiness flightBusiness;

    @Override
    public GetPersonnelResponse getById(Integer id) {

        return personnelMapper.mapToGetPersonnelResponse(personnelRepository
                .findById(id)
                .orElseThrow(() ->
                        new GeneralException(PersonnelEnum.PERSONNEL_NOT_EXISTS_BY_ID)));
    }

    @Override
    public List<GetPersonnelResponse> getAll() {
        return personnelRepository.findAll()
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }

    @Override
    public CreatePersonnelRequest add(CreatePersonnelRequest personnelRequest) {
        Personnel personnel = personnelMapper
                .mapToPersonnelFromCreatePersonnelRequest(personnelRequest);
        personnel.setBirthdate(LocalDate.parse(personnelRequest.getBirthdate(),
                DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        personnel.setBirthdate(LocalDate.parse(personnelRequest.getHireDate(),
                DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        personnelRepository.save(personnel);
        return personnelRequest;
    }

    @Transactional
    @Override
    public GetPersonnelResponse deleteById(Integer id) {
        Personnel personnel = personnelRepository.findById(id)
                .orElseThrow(() -> new GeneralException(PersonnelEnum.PERSONNEL_NOT_EXISTS_BY_ID));
        personnelRepository.deleteById(id);
        return personnelMapper.mapToGetPersonnelResponse(personnel);
    }

    @Transactional
    @Override
    public GetPersonnelResponse update(Integer id, UpdatePersonnelRequest request) {
        Personnel personnel = personnelRepository.findById(id)
                .orElseThrow(() -> new GeneralException(PersonnelEnum.PERSONNEL_NOT_EXISTS_BY_ID));
        personnelMapper.mapForUpdate(personnel, request);
        personnel.setBirthdate(LocalDate.parse(request.getBirthdate(),
                DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        personnel.setHireDate(LocalDate.parse(request.getHireDate(),
                DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        personnelRepository.save(personnel);
        return personnelMapper.mapToGetPersonnelResponse(personnel);
    }

    @Override
    public List<GetPersonnelResponse> getAllMen() {
        return personnelRepository.findAllByGender((byte) 1)
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }

    @Override
    public List<GetPersonnelResponse> getAllWomen() {
        return personnelRepository.findAllByGender((byte) 0)
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }


    @Override
    public List<GetPersonnelResponse> getByPosition(String position) {
        return personnelRepository.findByPosition(position)
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }

    @Override
    public List<GetPersonnelResponse> getByAge(Integer age) {
        return personnelRepository
                .findByBirthdate(LocalDate.now()
                        .minusYears(age))
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }

    @Override
    public List<GetPersonnelResponse> getByHireDateBefore(String date) {
        return personnelRepository
                .findByHireDateBefore(LocalDate.parse(date,
                        DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }

    @Override
    public List<GetPersonnelResponse> getByHireDateAfter(String date) {
        return personnelRepository
                .findByHireDateAfter(LocalDate.parse(date,
                        DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }

    @Override
    public List<GetPersonnelResponse> getBySalaryGreaterThan(Double salary) {
        return personnelRepository.findBySalaryGreaterThan(salary)
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }

    @Override
    public List<GetPersonnelResponse> getBySalaryLessThan(Double salary) {
        return personnelRepository.findBySalaryLessThan(salary)
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }

    @Override
    public List<GetPersonnelResponse> getByFlightId(Integer flightId) {
        flightBusiness.checkFlightExistsById(flightId);
        return personnelRepository.findByFlight_Id(flightId)
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }

    @Override
    public List<GetPersonnelResponse> getByFirstName(String firstName) {
        return personnelRepository
                .findByFirstName(firstName)
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }

    @Override
    public List<GetPersonnelResponse> getByLastName(String lastName) {
        return personnelRepository.findByLastName(lastName)
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }

    @Override
    public List<GetPersonnelResponse> getByFullName(String fullName) {
        String[] info = fullName.split(" ");
        return personnelRepository.findAllByLastNameAndFirstName(info[1], info[0])
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }

    @Override
    public List<GetPersonnelResponse> getByGender(String gender) {
        byte gender2 = 1;
        if (gender.equalsIgnoreCase("women"))
            gender2 = 0;
        else if (!gender.equalsIgnoreCase("men"))
            throw new GeneralException(CustomerEnum.INVALID_GENDER);
        return personnelRepository.findAllByGender(gender2)
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }

    @Override
    public List<GetPersonnelResponse> getByBirthdate(String birthDate) {
        return personnelRepository.findByBirthdate(LocalDate
                        .parse(birthDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .stream()
                .map(personnelMapper::mapToGetPersonnelResponse)
                .toList();
    }
}