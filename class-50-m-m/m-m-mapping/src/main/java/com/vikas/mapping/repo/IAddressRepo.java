package com.vikas.mapping.repo;

import com.vikas.mapping.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAddressRepo extends JpaRepository<Address,Integer> {
}
