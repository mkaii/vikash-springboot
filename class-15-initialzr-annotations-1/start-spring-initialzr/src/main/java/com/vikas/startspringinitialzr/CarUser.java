package com.vikas.startspringinitialzr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarUser {

    @Autowired
    Car myCar;

    @GetMapping("car")
    public Car getCar()
    {
        return myCar;
    }



}
