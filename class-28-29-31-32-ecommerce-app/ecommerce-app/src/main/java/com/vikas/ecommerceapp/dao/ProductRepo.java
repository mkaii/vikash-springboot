package com.vikas.ecommerceapp.dao;

import com.vikas.ecommerceapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ProductRepo {

    @Autowired
    private  Map<Integer, Product> productList;

    public Map<Integer, Product> getProducts() {
       return  productList;
    }
}
