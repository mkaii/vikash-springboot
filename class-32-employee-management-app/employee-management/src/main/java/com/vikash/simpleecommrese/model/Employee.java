package com.vikash.simpleecommrese.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {


    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private LocalDate userDateOfBirth;
    private Gender gender;
    private Double salary;
}
