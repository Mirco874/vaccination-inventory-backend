package com.mirco.employeeControl.service.controll;

import com.mirco.employeeControl.model.entity.User;
import com.mirco.employeeControl.model.entity.VaccinationRecord;
import com.mirco.employeeControl.model.pojo.dto.controll.*;
import com.mirco.employeeControl.model.pojo.vo.controll.CreatedEmployedVo;
import com.mirco.employeeControl.model.pojo.vo.controll.EmployeeVo;
import com.mirco.employeeControl.model.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.mirco.employeeControl.commons.utils.GenerateRandomPassword.generateRandomPassword;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository repository;
    private final VaccinationRecordService vaccinationRecordService;

    @Autowired
    public UserServiceImplementation(UserRepository repository, VaccinationRecordService vaccinationRecordService) {
        this.repository = repository;
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
        String randomPassword = generateRandomPassword(15);

        employee.setPassword(randomPassword);
        employee.setIdRol(1);

        User savedEmployee = repository.save(employee);

        CreatedEmployedVo createdEmployedVo = new CreatedEmployedVo();
        BeanUtils.copyProperties( savedEmployee, createdEmployedVo );

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

            updateEmployeRecord(dto, optionalEmployee.get());

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


    public void updateEmployeRecord (UpdateEmployeeInfoDto dto, User employee ){
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
        employeeVo.setFullName((String) employeeObject[1]);
        employeeVo.setIdentityCard((String) employeeObject[2]);
        employeeVo.setEmail((String) employeeObject[3]);
        employeeVo.setBirthDate((Date) employeeObject[4]);
        employeeVo.setAddress((String) employeeObject[5]);
        employeeVo.setPhone((String) employeeObject[6]);

        employeeVo.setVaccinatedState( employeeObject[9]!=null );

        employeeVo.setVaccineType((String) employeeObject[7]);
        employeeVo.setVaccinationDate((Date) employeeObject[8]);
        employeeVo.setDoses((Integer)employeeObject[9]);

        return employeeVo;
    }


}
