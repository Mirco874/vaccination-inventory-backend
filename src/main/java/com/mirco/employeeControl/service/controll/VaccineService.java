package com.mirco.employeeControl.service.controll;

import com.mirco.employeeControl.model.entity.Role;
import com.mirco.employeeControl.model.entity.Vaccine;
import com.mirco.employeeControl.model.pojo.vo.controll.VaccineVo;

import java.util.List;
import java.util.Optional;

public interface VaccineService {
    Optional<Vaccine> findById(int id);
    List<VaccineVo> findAllVo();
    List<Vaccine> findAll();


}
