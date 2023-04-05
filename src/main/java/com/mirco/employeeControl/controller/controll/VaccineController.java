package com.mirco.employeeControl.controller.controll;

import com.mirco.employeeControl.commons.ResultResponse;
import com.mirco.employeeControl.model.entity.Vaccine;
import com.mirco.employeeControl.model.enums.HttpResponseMessage;
import com.mirco.employeeControl.model.pojo.vo.controll.VaccineVo;
import com.mirco.employeeControl.service.controll.VaccineService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/vaccine")
public class VaccineController {
    private final VaccineService service;

    @Autowired
    public VaccineController(VaccineService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") int id) {

        Optional<Vaccine> vaccine = service.findById(id);

        if (vaccine.isPresent()) {
            VaccineVo vaccineVo = new VaccineVo();
            BeanUtils.copyProperties(vaccine.get(), vaccineVo);
            return new ResponseEntity<>(ResultResponse.builder()
                    .status(true)
                    .message(HttpResponseMessage.FIND_SUCCESSFUL.getValue())
                    .data(vaccineVo).build(), HttpStatus.OK);

        }
        return new ResponseEntity<>(ResultResponse
                .builder()
                .status(false)
                .message(HttpResponseMessage.NOT_FOUND_RECORD.getValue())
                .build(), HttpStatus.OK);

    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<VaccineVo> vaccineVoList = service.findAllVo();

        return new ResponseEntity<>(ResultResponse.builder()
                .status(true)
                .message(HttpResponseMessage.FIND_SUCCESSFUL.getValue())
                .data(vaccineVoList)
                .build(), HttpStatus.OK);
    }

}
