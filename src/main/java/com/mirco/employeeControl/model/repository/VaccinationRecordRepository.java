package com.mirco.employeeControl.model.repository;

import com.mirco.employeeControl.model.entity.VaccinationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Integer> {

    @Query(value = "SELECT * FROM controll.vaccination_record v WHERE v.id_user=:id  ", nativeQuery = true)
    Optional<VaccinationRecord> findByIdUser(@Param("id") int id);
}
