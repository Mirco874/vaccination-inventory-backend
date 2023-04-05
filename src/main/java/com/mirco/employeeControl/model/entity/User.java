package com.mirco.employeeControl.model.entity;


import com.mirco.employeeControl.model.SchemaDB;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import org.springframework.security.core.context.SecurityContextHolder;

@Entity
@Table(schema = SchemaDB.CONTROL, name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_rol")
    private int idRol;

    @Column(name = "identity_card")
    private String identityCard;

    private String email;

    private String password;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private String address;
    private String phone;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;


    @PrePersist
    public void prePersist() {
        this.createdBy = SecurityContextHolder.getContext().getAuthentication().getName();
        this.createdDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedBy = SecurityContextHolder.getContext().getAuthentication().getName();
        this.updatedDate = new Date();
    }



}
