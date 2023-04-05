package com.mirco.employeeControl.model.entity;

import com.mirco.employeeControl.model.SchemaDB;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = SchemaDB.CONTROL, name = "vaccination_record")
@Getter
@Setter
public class VaccinationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_user")
    private int idUser;

    @Column(name = "id_vaccine")
    private int idVaccine;

    @Column(name = "vaccination_date")
    @Temporal(TemporalType.DATE)
    private Date vaccinationDate;

    private int doses;

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
