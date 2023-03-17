package com.mirco.employeeControl.service.controll;

import com.mirco.employeeControl.model.entity.VaccinationRecord;
import com.mirco.employeeControl.model.pojo.dto.controll.VaccinationRecordDto;
import com.mirco.employeeControl.model.repository.VaccinationRecordRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VaccinationRecordServiceImplementation implements VaccinationRecordService {

    private final VaccinationRecordRepository repository;

    @Autowired
    public VaccinationRecordServiceImplementation(VaccinationRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<VaccinationRecord> findByUserId(int id) {
        return repository.findByIdUser(id);
    }

    @Override
    public VaccinationRecord persistVaccinationRecord(VaccinationRecordDto dto) {
        VaccinationRecord vaccinationRecord = new VaccinationRecord();
        BeanUtils.copyProperties( dto, vaccinationRecord );
        return (repository.save(vaccinationRecord));
    }

    @Override
    public void updateVaccinationRecord(VaccinationRecord entity) {
        repository.save(entity);
    }
}
