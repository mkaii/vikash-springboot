package com.vikas.ecommerceapp.controller;

import com.vikas.ecommerceapp.model.Category;
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

    //get products in a particular price range :

    @GetMapping("products/range/lower/{lowerPrice}/higher/{higherPrice}")
    public List<Product> getProductsInPriceRange(@PathVariable double lowerPrice,@PathVariable double higherPrice)
    {
        return productService.getProductsInPriceRange(lowerPrice,higherPrice);
    }


    @GetMapping("products/count")
    public Integer getProductCount()
    {
        return productService.getProductCount();

    }



    @GetMapping("products/category/{category}")
    public  List<Product> getProductByCategory(@PathVariable Category category)
    {
        return productService.getProductByCategory(category);

    }

    @GetMapping("products/category/{category}/count")
    public long getProductCountByCategory(@PathVariable Category category)
    {
        return productService.getProductCountByCategory(category);

    }

    @GetMapping("products/range/lower/{lowerPrice}/higher/{higherPrice}/category")
    public List<Product> getProductsInPriceRangeByCategory(@PathVariable double lowerPrice,@PathVariable double higherPrice, @RequestParam Category category)
    {
        return productService.getProductsInPriceRangeByCategory(lowerPrice,higherPrice,category);
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
