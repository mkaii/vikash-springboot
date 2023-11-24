package com.vikash.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class BeanFactory {

    public BeanFactory(){
        System.out.println("config object is getting created "); // just to verify if config will create object
    }

    @Bean
   public Car getRedCar(){
        System.out.println("inside red car method"); // just to verify that method is getting called automatically.
        return new Car("red");
    }

    @Bean
    public Car getBlueCar(){
        System.out.println("inside blue car method"); // just to verify that method is getting called automatically.
        return new Car("blue");
    }
}
