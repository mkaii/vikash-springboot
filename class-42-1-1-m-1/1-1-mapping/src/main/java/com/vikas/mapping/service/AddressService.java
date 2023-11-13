package com.vikas.mapping.service;


import com.vikas.mapping.model.Address;
import com.vikas.mapping.repo.IAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    IAddressRepo addressRepo;

    public String addAddress(Address newAddress) {
        addressRepo.save(newAddress);
        return "address added";
    }
}
