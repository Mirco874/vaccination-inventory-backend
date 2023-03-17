package com.mirco.employeeControl.service.controll;

import com.mirco.employeeControl.model.entity.Vaccine;
import com.mirco.employeeControl.model.pojo.vo.controll.VaccineVo;
import com.mirco.employeeControl.model.repository.VaccineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineServiceImplementation implements VaccineService{

    private final VaccineRepository repository;

    @Autowired
    public VaccineServiceImplementation(VaccineRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Vaccine> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<VaccineVo> findAllVo() {
        List<VaccineVo> voList = new ArrayList<>();
        List<Vaccine> vaccineList = repository.findAll();

        for( Vaccine vaccine: vaccineList ){
            VaccineVo vaccineVo= new VaccineVo();
            BeanUtils.copyProperties(vaccine,vaccineVo);
            voList.add(vaccineVo);
        }
        return voList;
    }

    @Override
    public List<Vaccine> findAll() {
        return repository.findAll();
    }
}
