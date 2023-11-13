package com.vikas.mapping.repo;

import com.vikas.mapping.model.Address;
import com.vikas.mapping.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpRepo extends JpaRepository<Emp,Integer> {
}
