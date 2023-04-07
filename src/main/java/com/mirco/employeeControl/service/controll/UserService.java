package com.mirco.employeeControl.service.controll;


import com.mirco.employeeControl.model.entity.User;
import com.mirco.employeeControl.model.pojo.dto.controll.*;
import com.mirco.employeeControl.model.pojo.vo.controll.CreatedEmployedVo;
import com.mirco.employeeControl.model.pojo.vo.controll.EmployeeVo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<EmployeeVo> findAllUsers();
    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
    Optional<EmployeeVo> findEmployeeVoById(int id);
    Optional<CreatedEmployedVo> persistEmployee (CreateEmployeeDto dto);
    void editEmployeeIdentifyingInformation(int id, EditEmployeeDto dto);
    void updateEmployeeVaccinationInformation (int id, UpdateVaccinationInfoDto dto);
    void removeUser(User user);
    Boolean existsUserByEmail(String email);
    Boolean existsUserByIdentityCard(String identityCard);
    void updatePersonalInformation(int id, UpdatePersonalInfoDto dto);
}
