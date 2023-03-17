package com.mirco.employeeControl.model.pojo.dto.controll;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
public class EditEmployeeDto {
    private int identityCard;
    private String email;
    private String name;
    private String lastName;

    private Date birthDate;
    private String address;
    private String phone;
}
