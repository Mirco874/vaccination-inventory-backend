package com.mirco.employeeControl.model.pojo.dto.controll;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateEmployeeInfoDto {
    private Date birthDate;
    private String address;
    private String phone;
    private boolean isVaccinated;
    private int idVaccine;
    private Date vacccinationDate;
    private int doses;
}
