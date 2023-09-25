package com.vikash.simpleecommrese.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {


    @NotNull
    private Integer id;


    @NotEmpty(message = "name should not be empty!!!!")
    private String firstname;


    @NotBlank(message = "last name cannot be empty!!!")
    private String lastname;

    @Email(message = "input is not a valid email id !!!")
    private String email;

    @Size(min=8)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@$!%*?&#])[A-Z][A-Za-z0-9@$!%*?&#]+$", message = "password is not strong enough!!!")
    private String password;
    private LocalDate userDateOfBirth;
    private Gender gender;
    private Double salary;
}
