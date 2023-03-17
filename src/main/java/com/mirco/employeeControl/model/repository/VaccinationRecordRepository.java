package com.mirco.employeeControl.model.repository;

import com.mirco.employeeControl.model.entity.VaccinationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Integer> {
}
