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

   /* public String removeAddressById(Integer id) {

        Address address = addressRepo.findById(id).orElseThrow();

        Emp emp = empRepo.findFirstByAddress(address);


        if(emp!= null)
        {
            return emp.getEmpName() + " linked with address id" + id;
        }

        addressRepo.deleteById(id);
        return "address deleted";

    }*/
}
