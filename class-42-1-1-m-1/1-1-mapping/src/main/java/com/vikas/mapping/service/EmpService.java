package com.vikas.mapping.service;

import com.vikas.mapping.model.Emp;
import com.vikas.mapping.repo.IEmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmpService {

    @Autowired
    IEmpRepo empRepo;

    public String addEmp(Emp newEmp) {

        empRepo.save(newEmp);
        return "add employee";
    }
}
