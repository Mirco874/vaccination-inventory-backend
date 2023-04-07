package com.mirco.employeeControl.model.pojo.vo.controll;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class EmployeeVo {
    Integer id;
    String name;
    String lastName;
    String identityCard;
    String email;
    Date birthDate;
    String address;
    String phone;

    boolean vaccinatedState;
    String vaccineType;
    Date vaccinationDate;
    Integer doses;
}
