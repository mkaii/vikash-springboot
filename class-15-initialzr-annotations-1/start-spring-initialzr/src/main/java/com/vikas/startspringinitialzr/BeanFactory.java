package com.vikas.startspringinitialzr;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class BeanFactory {

    public BeanFactory()
    {
        System.out.println("config object is getting created!!!!");
    }
    @Bean
    public Car getRedCar()
    {
        System.out.println("inside red car get method");
        return new Car("Red");
    }


    public Car getBlueCar()
    {
        System.out.println("inside blue car get method");
        return new Car("Blue");
    }
}
