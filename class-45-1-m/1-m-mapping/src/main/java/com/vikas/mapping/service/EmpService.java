package com.vikas.mapping.service;

import com.vikas.mapping.model.Address;
import com.vikas.mapping.model.Emp;
import com.vikas.mapping.repo.IEmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpService {

    @Autowired
    IEmpRepo empRepo;

    @Autowired
    AddressService addressService;

    public String addEmp(Emp newEmp) {

        //newEmp.setAddress(null);
        empRepo.save(newEmp);
        return "add employee";
    }

    public List<Emp> getAllEmp() {
        return empRepo.findAll();
    }

    /*public String linkAddressToEmployee(Integer eId, Integer aId) {

        Emp emp = empRepo.findById(eId).orElseThrow();

        Address address = addressService.getAddressById(aId);

        emp.setAddress(address);

        empRepo.save(emp);

        return "linked";

    }*/

   /* public List<Emp> getEmployeesForThisAddress(Integer addressId) {

        Address address = addressService.getAddressById(addressId);

        return empRepo.findByAddress(address);

    }*/
}
