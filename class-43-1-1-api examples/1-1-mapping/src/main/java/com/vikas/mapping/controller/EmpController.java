package com.vikas.mapping.controller;


import com.vikas.mapping.model.Address;
import com.vikas.mapping.model.Emp;
import com.vikas.mapping.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpController {

    @Autowired
    EmpService empService;

    @PostMapping("emp")
    public String addEmp(@RequestBody Emp newEmp)
    {
        return empService.addEmp(newEmp);
    }

    @GetMapping("emp")
    public List<Emp> getAllEmp()
    {
        return empService.getAllEmp();
    }


    @PutMapping("employee/{id1}/address/{id2}")
    public String linkAddressToEmployee(@PathVariable Integer id1,@PathVariable Integer id2)
    {
       return empService.linkAddressToEmployee(id1,id2);
    }


    @GetMapping("employess/address/{addressId}")
    public List<Emp> getEmployeesForThisAddress(@PathVariable Integer addressId)
    {
        return empService.getEmployeesForThisAddress(addressId);
    }
}
