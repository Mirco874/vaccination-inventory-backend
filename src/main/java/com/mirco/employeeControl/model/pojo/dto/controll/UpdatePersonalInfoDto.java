package com.mirco.employeeControl.model.pojo.dto.controll;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdatePersonalInfoDto {
    private Date birthDate;
    private String address;
    private String phone;
}
