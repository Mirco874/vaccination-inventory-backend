package com.mirco.employeeControl.service.controll;

import com.mirco.employeeControl.model.entity.User;
import com.mirco.employeeControl.model.entity.VaccinationRecord;
import com.mirco.employeeControl.model.pojo.dto.controll.*;
import com.mirco.employeeControl.model.pojo.vo.controll.CreatedEmployedVo;
import com.mirco.employeeControl.model.pojo.vo.controll.EmployeeVo;
import com.mirco.employeeControl.model.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.mirco.employeeControl.commons.utils.GenerateRandomPassword.generateRandomPassword;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final VaccinationRecordService vaccinationRecordService;

    @Autowired
    public UserServiceImplementation(UserRepository repository, PasswordEncoder passwordEncoder, VaccinationRecordService vaccinationRecordService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.vaccinationRecordService = vaccinationRecordService;
    }

    @Override
    public List<EmployeeVo> findAllUsers() {

        List<Object[]> employeesList = repository.findAllEmployees();

        List<EmployeeVo> result = new ArrayList<>(employeesList.size());

        for (Object[] object : employeesList) {
            EmployeeVo employeeVo = employeeObjectToVo(object);
            result.add(employeeVo);
        }
    return  result;
    }

    @Override
    public Optional<User> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<EmployeeVo> findEmployeeVoById(int id) {
        Optional<Object[]> employeeOptionalObject = repository.findEmployeeById(id);

        if(employeeOptionalObject.isPresent()){
            Object[] objectList = (Object[]) employeeOptionalObject.get()[0];
            EmployeeVo employeeVo = employeeObjectToVo(objectList);
            return Optional.of(employeeVo);
        }

        return Optional.empty();
    }

    @Override
    public Optional<CreatedEmployedVo> persistEmployee(CreateEmployeeDto dto) {
        User employee = new User();
        BeanUtils.copyProperties( dto, employee );
        String randomPassword = generateRandomPassword(10);
        employee.setPassword(passwordEncoder.encode(randomPassword));
        employee.setIdRol(1);

        User savedEmployee = repository.save(employee);

        CreatedEmployedVo createdEmployedVo = new CreatedEmployedVo();

        createdEmployedVo.setEmail(savedEmployee.getEmail());
        createdEmployedVo.setPassword(randomPassword);

        return Optional.of(createdEmployedVo);
    }

    @Override
    public void editEmployeeIdentifyingInformation(int id, EditEmployeeDto dto) {
        Optional<User> optionalEmployee = repository.findById(id);

        if(!optionalEmployee.isEmpty()){
            BeanUtils.copyProperties( dto, optionalEmployee.get() );
            repository.save(optionalEmployee.get());
        }
    }

    @Override
    public void updateEmployeeAdditionalInformation(int id, UpdateEmployeeInfoDto dto) {
        Optional<User> optionalEmployee = repository.findById(id);
        Optional<VaccinationRecord> optionalVaccinationRecord = vaccinationRecordService.findByUserId(id);

        if(optionalEmployee.isPresent()){

            updateEmployeeRecord(dto, optionalEmployee.get());

            if(optionalVaccinationRecord.isEmpty() && dto.isVaccinated()){
                createVaccRecord(id, dto);
            }
            else{
                updateVacRecord(dto,optionalVaccinationRecord.get());
            }
        }

    }

    @Override
    public void removeUser(User user) {
        repository.delete(user);
    }

    @Override
    public Boolean existsUserByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Boolean existsUserByIdentityCard(String identityCard) {
        return repository.existsByIdentityCard(identityCard);
    }

    public void updateEmployeeRecord (UpdateEmployeeInfoDto dto, User employee ){
        BeanUtils.copyProperties( dto, employee );
        repository.save(employee);
    }

    public void createVaccRecord (int userId, UpdateEmployeeInfoDto dto) {
        VaccinationRecordDto vaccinationRecordDto = new VaccinationRecordDto();
        vaccinationRecordDto.setIdUser(userId);
        BeanUtils.copyProperties(dto,vaccinationRecordDto);
        vaccinationRecordService.persistVaccinationRecord(vaccinationRecordDto);
    }

    public void updateVacRecord ( UpdateEmployeeInfoDto dto, VaccinationRecord vaccinationRecord ) {
        BeanUtils.copyProperties( dto, vaccinationRecord );
        vaccinationRecordService.updateVaccinationRecord(vaccinationRecord);
    }

    public EmployeeVo employeeObjectToVo (Object[] employeeObject) {
        EmployeeVo employeeVo = new EmployeeVo();

        employeeVo.setId((Integer) employeeObject[0]);
        employeeVo.setName((String) employeeObject[1]);
        employeeVo.setLastName((String) employeeObject[2]);
        employeeVo.setIdentityCard((String) employeeObject[3]);
        employeeVo.setEmail((String) employeeObject[4]);
        employeeVo.setBirthDate((Date) employeeObject[5]);
        employeeVo.setAddress((String) employeeObject[6]);
        employeeVo.setPhone((String) employeeObject[7]);

        employeeVo.setVaccinatedState( employeeObject[10]!=null );

        employeeVo.setVaccineType((String) employeeObject[8]);
        employeeVo.setVaccinationDate((Date) employeeObject[9]);
        employeeVo.setDoses((Integer)employeeObject[10]);

        return employeeVo;
    }


}
