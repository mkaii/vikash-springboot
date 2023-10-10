package com.vikash.mapping.service;

import com.vikash.mapping.model.Address;
import com.vikash.mapping.model.Employee;
import com.vikash.mapping.repo.IRepoAddress;
import com.vikash.mapping.repo.IRepoEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressService {

    @Autowired
    IRepoAddress iRepoAddress;

    @Autowired
    IRepoEmployee  iRepoEmployee;

    public String addAddress(Address address) {
       iRepoAddress.save(address);
        return "added address";
    }

    public String addAddresses(List<Address> addresses) {
        iRepoAddress.saveAll(addresses);
        return "added addresses";
    }

    public Iterable<Address> getAllAddresses() {
        return iRepoAddress.findAll();
    }

    public Address getAdressById(Integer aid2) {
        return iRepoAddress.findById(aid2).orElseThrow();
    }

    public String removeAllAdresses() {
        iRepoAddress.deleteAll();
        return "all adresses removed";
    }

    public String removeAddressById(Integer id) {
        Employee employee = iRepoEmployee.findById(id).orElseThrow();
        Address address = employee.getAddress();
        //if (employee.getAddress() != null && employee.getAddress().getAddressId() != null && !employee.getEmpId().equals(employee.getAddress().getAddressId())) {
        if (employee.getEmpId() != null && employee.getAddress() != null && employee.getEmpId().equals(address.getAddressId())) {
            iRepoAddress.deleteById(id);
            return "address was deleted";
        } else {
            return " you have to unlink first";
        }
    }



    public String removeAddressesById(List<Integer> ids) {
        iRepoAddress.deleteAllById(ids);
        return  "addresses was deleted";
    }
}



