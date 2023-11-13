package com.vikas.mapping.service;


import com.vikas.mapping.model.Address;
import com.vikas.mapping.model.Emp;
import com.vikas.mapping.repo.IAddressRepo;
import com.vikas.mapping.repo.IEmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    IAddressRepo addressRepo;

    @Autowired
    IEmpRepo empRepo;

    public String addAddress(Address newAddress) {
        addressRepo.save(newAddress);
        return "address added";
    }

    public List<Address> getAllAddresses() {

        return addressRepo.findAll();
    }

    public Address getAddressById(Integer aId) {

        return addressRepo.findById(aId).orElseThrow();
    }

    public String linkEmployees(Integer addId, List<Integer> empIds) {

        Address add = addressRepo.findById(addId).orElseThrow();

       List<Emp> empList =  empRepo.findAllById(empIds);

        add.setEmployees(empList);

        addressRepo.save(add);


        return "linking done";

    }

    public String removeAddress(Integer id) {

        Address add = addressRepo.findById(id).orElseThrow();

        if (add.getEmployees().size() > 0) {
            return "cannot delete!!";
        }
        else
        {
            addressRepo.delete(add);
            return "address removed";
        }
    }


}
