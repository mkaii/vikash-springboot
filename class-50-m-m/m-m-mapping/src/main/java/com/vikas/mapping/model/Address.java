package com.vikas.mapping.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    private String addressName;


    @ManyToMany
    @JoinTable(name = "fk_join_table",joinColumns = @JoinColumn(name = "fk_add_id"),inverseJoinColumns = @JoinColumn(name = "fk_emp_id"))
    List<Emp> employees;
}
