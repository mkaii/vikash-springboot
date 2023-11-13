package com.vikas.mapping.controller;


import com.vikas.mapping.model.Emp;
import com.vikas.mapping.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {

    @Autowired
    EmpService empService;

    @PostMapping("emp")
    public String addEmp(@RequestBody Emp newEmp)
    {
        return empService.addEmp(newEmp);
    }
}
