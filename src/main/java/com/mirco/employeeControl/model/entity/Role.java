package com.mirco.employeeControl.model.entity;


import com.mirco.employeeControl.model.SchemaDB;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = SchemaDB.CONTROL, name = "rol")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
}
