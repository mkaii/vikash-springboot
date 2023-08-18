package com.vikash.demo;

import org.springframework.stereotype.Component;

@Component
public class Car {
    private String color;

    public Car(){
        System.out.println("created using component");  // object of this class was created using contructor
        this.color="golden";
    }

    public Car(String color) {
        this.color = color;
        System.out.println("the color of my car "+ this.color);  // to see if object is created of the getRedCar() because if it is created it will call the contructor.
                                                              // method wont work without @bean
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
