package com.vikas.mapping.controller;


import com.vikas.mapping.model.Address;
import com.vikas.mapping.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    AddressService addressService;


    @PostMapping("address")
    public String addAddress(@RequestBody Address newAddress)
    {
        return addressService.addAddress(newAddress);
    }

    @GetMapping("address")
    public List<Address> getAllAddresses()
    {
        return addressService.getAllAddresses();
    }


    @PutMapping("link/employees/address/{addId}")
    public String linkEmployees(@PathVariable Integer addId, @RequestBody List<Integer> empIds)
    {
        return addressService.linkEmployees(addId,empIds);
    }

    @DeleteMapping("address/{id}")
    public String removeAddress(@PathVariable Integer id)
    {
        return addressService.removeAddress(id);
    }
}
