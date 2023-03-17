package com.mirco.employeeControl.model.pojo.dto.controll;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FilterEmployeesDto {
    boolean vaccinatedState;
    int vaccineType;
    Date startDate;
    Date endDate;

}
