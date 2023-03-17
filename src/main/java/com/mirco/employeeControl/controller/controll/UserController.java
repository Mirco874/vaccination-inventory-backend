package com.mirco.employeeControl.controller.controll;

import com.mirco.employeeControl.model.entity.User;

import com.mirco.employeeControl.model.pojo.dto.controll.CreateEmployeeDto;
import com.mirco.employeeControl.model.pojo.dto.controll.EditEmployeeDto;
import com.mirco.employeeControl.model.pojo.dto.controll.UpdateEmployeeInfoDto;
import com.mirco.employeeControl.model.pojo.vo.controll.CreatedEmployedVo;
import com.mirco.employeeControl.model.pojo.vo.controll.EmployeeVo;
import com.mirco.employeeControl.service.controll.UserService;
import com.pos.pos.model.enums.HttpResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/employee")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable("id") int id) {

        Optional<EmployeeVo> optionalEmployeeVo = service.findEmployeeVoById(id);

        if (optionalEmployeeVo.isPresent()) {
            return new ResponseEntity<>(com.pos.pos.commons.ResultResponse.builder()
                    .status(true)
                    .message(com.pos.pos.model.enums.HttpResponseMessage.FIND_SUCCESSFUL.getValue())
                    .data(optionalEmployeeVo).build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(com.pos.pos.commons.ResultResponse
                .builder()
                .status(false)
                .message(com.pos.pos.model.enums.HttpResponseMessage.NOT_FOUND_RECORD.getValue())
                .build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<EmployeeVo> employeeVoList = service.findAllUsers();
        return new ResponseEntity<>(com.pos.pos.commons.ResultResponse.builder()
                .status(true)
                .message(com.pos.pos.model.enums.HttpResponseMessage.FIND_SUCCESSFUL.getValue())
                .data(employeeVoList).build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> persist(@RequestBody CreateEmployeeDto dto) {
        Optional<CreatedEmployedVo> createdEmployedVoOptional = service.persistEmployee(dto);

        if(createdEmployedVoOptional.isPresent()){
            return new ResponseEntity<>(com.pos.pos.commons.ResultResponse.builder()
                    .status(true)
                    .message(HttpResponseMessage.PERSIST_SUCCESSFUL.getValue())
                    .data(createdEmployedVoOptional.get()).build(), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(com.pos.pos.commons.ResultResponse.builder()
                .status(false)
                .message(HttpResponseMessage.PERSIST_FAILED.getValue())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}/update-identity-information")
    public ResponseEntity<?> updateIdentifyingInformation(@PathVariable("id") int id, @RequestBody EditEmployeeDto dto) {
        service.editEmployeeIdentifyingInformation(id, dto);

        return new ResponseEntity<>(com.pos.pos.commons.ResultResponse.builder()
                .status(true)
                .message(HttpResponseMessage.UPDATE_SUCCESSFUL.getValue())
                .build(), HttpStatus.OK);
    }

    @PutMapping("/{id}/update-additional-information")
    public ResponseEntity<?> updateAdditionalInformation(@PathVariable("id") int id, @RequestBody UpdateEmployeeInfoDto dto) {
        service.updateEmployeeAdditionalInformation(id, dto);

        return new ResponseEntity<>(com.pos.pos.commons.ResultResponse.builder()
                .status(true)
                .message(HttpResponseMessage.UPDATE_SUCCESSFUL.getValue())
                .build(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        Optional<User> optionalUser = service.findById(id);
        if (optionalUser.isPresent()) {
            service.removeUser(optionalUser.get());
            return new ResponseEntity<>(com.pos.pos.commons.ResultResponse.builder()
                    .status(true)
                    .message(HttpResponseMessage.DELETE_SUCCESSFUL.getValue())
                    .build(), HttpStatus.OK);

        }
        return new ResponseEntity<>(com.pos.pos.commons.ResultResponse
                .builder()
                .status(false)
                .message(HttpResponseMessage.NOT_FOUND_RECORD.getValue())
                .build(), HttpStatus.OK);
    }







}
