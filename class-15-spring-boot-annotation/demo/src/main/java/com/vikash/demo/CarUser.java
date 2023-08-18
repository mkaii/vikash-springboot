package com.vikash.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarUser {
@Autowired
    Car myCar;  //you dont have tp creat new object object is already craeted via constructor as we can see in log lines
///myCar needs to point to object of type Car -> wil assign value golden to myCar _> this is dependecny injection

    @GetMapping("car") // gave it an endpoint
    public Car getCar(){ // created a method
        return myCar;    // will return assigned value for myCar
    }
}
