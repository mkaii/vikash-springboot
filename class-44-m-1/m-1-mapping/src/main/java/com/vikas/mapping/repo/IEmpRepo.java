package com.vikas.mapping.repo;

import com.vikas.mapping.model.Address;
import com.vikas.mapping.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmpRepo extends JpaRepository<Emp,Integer> {
    List<Emp> findByAddress(Address address);

    Emp findFirstByAddress(Address address);
}
