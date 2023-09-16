package com.vikash.simpleecommrese.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    private Integer id;
    private String name;
    private double price;
    private Category Category;

}
