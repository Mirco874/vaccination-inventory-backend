package com.mirco.employeeControl.service.controll;

import com.mirco.employeeControl.model.entity.VaccinationRecord;
import com.mirco.employeeControl.model.pojo.dto.controll.VaccinationRecordDto;

import java.util.Optional;

public interface VaccinationRecordService {
    Optional<VaccinationRecord> findByUserId(int id);
    VaccinationRecord persistVaccinationRecord (VaccinationRecordDto dto);
    void updateVaccinationRecord( VaccinationRecord entity );

}
