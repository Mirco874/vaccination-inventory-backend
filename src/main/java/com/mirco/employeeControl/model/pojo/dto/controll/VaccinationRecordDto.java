package com.mirco.employeeControl.model.pojo.dto.controll;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VaccinationRecordDto {
    private int idUser;
    private int idVaccine;
    private Date vaccinationDate;
    private int doses;
}
