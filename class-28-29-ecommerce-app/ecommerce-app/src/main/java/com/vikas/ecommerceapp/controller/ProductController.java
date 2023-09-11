package com.vikas.ecommerceapp.controller;

import com.vikas.ecommerceapp.model.Product;
import com.vikas.ecommerceapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    //APIs

    //GET

    @GetMapping("products")
    public Map<Integer, Product> getAllProducts()
    {
        return productService.getAllProducts();

    }

    @GetMapping("products/Id/{id}")
    public Product getProductById(@PathVariable Integer  id)
    {
        return productService.getProductById(id);

    }


    //POST


    @PostMapping("product")
    public String addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
        return "added";

    }


    @PostMapping("products")
    public String addProducts(@RequestBody List<Product> products)
    {
        productService.addProducts(products);
        return "added " + products.size() + " products";

    }


    //PUT


    //DELETE
}
