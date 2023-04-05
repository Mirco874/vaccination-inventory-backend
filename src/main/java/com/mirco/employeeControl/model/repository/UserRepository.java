package com.mirco.employeeControl.model.repository;

import com.mirco.employeeControl.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT \t\n" +
            "\tu.id, \n" +
            "\tCONCAT(u.name,' ',u.last_name), \n" +
            "\tu.identity_card, \n" +
            "\tu.email, \n" +
            "\tu.birth_date,\n" +
            "\tu.address, \n" +
            "\tu.phone,\n" +
            "\tv.name, \n" +
            "\tr.vaccination_date, \n" +
            "\tr.doses\n" +
            "FROM control.\"user\" u\n" +
            "INNER JOIN control.rol rl\n" +
            "ON u.id_rol = rl.id and rl.id=1\n" +
            "\n" +
            "LEFT JOIN control.vaccination_record r \n" +
            "ON u.id = r.id_user \n" +
            "\n" +
            "LEFT JOIN control.vaccine v\n" +
            "ON v.id = r.id_vaccine ", nativeQuery = true)
    List findAllEmployees();


    @Query(value = "SELECT \t\n" +
            "\tu.id, \n" +
            "\tCONCAT(u.name,' ',u.last_name), \n" +
            "\tu.identity_card, \n" +
            "\tu.email, \n" +
            "\tu.birth_date,\n" +
            "\tu.address, \n" +
            "\tu.phone,\n" +
            "\tv.name, \n" +
            "\tr.vaccination_date, \n" +
            "\tr.doses\n" +
            "FROM control.\"user\" u\n" +
            "INNER JOIN control.rol rl\n" +
            "ON u.id_rol = rl.id and u.id_rol=1 and u.id=:id\n" +
            "\n" +
            "LEFT JOIN control.vaccination_record r \n" +
            "ON u.id = r.id_user \n" +
            "\n" +
            "LEFT JOIN control.vaccine v\n" +
            "ON v.id = r.id_vaccine ", nativeQuery = true)
    Optional<Object[]> findEmployeeById( @Param("id") int id);
    Optional<User> findByEmail(String email);
}
