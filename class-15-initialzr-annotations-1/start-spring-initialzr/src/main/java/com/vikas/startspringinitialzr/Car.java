package com.vikas.startspringinitialzr;

import org.springframework.stereotype.Component;

@Component
public class Car {

    private String color;

    public Car()
    {
        System.out.println("created using component!!!");
        this.color = "Golden";
    }
    public Car(String color) {

        this.color = color;
        System.out.println("Color of my car is :" + this.color);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;

    }
}
