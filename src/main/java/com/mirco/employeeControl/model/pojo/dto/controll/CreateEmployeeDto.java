package com.mirco.employeeControl.model.pojo.dto.controll;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeDto {
    private String identityCard;
    private String email;
    private String name;
    private String lastName;
}
