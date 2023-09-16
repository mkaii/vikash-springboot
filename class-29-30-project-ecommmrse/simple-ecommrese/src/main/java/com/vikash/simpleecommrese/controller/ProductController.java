package com.vikash.simpleecommrese.controller;

import com.vikash.simpleecommrese.model.Category;
import com.vikash.simpleecommrese.model.Product;
import com.vikash.simpleecommrese.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    //GET

    // get all products
    @GetMapping("products")
    public Map<Integer, Product> getAllProducts(){
        return productService.getAllProducts();
    }

    // get product by id
    @GetMapping("products/id/{id}")
    public  Product getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    // get products in a particular price range
    @GetMapping("products/low/{low}/high/{high}")
    public List<Product> getProductsInPriceRange(@PathVariable double low,@PathVariable double high ){
        return productService.getProductsInPriceRange(low,high);
    }

    // get count oof all the prodcuts
    @GetMapping("products/count")
    public Integer getProductsCount(){
        return productService.getProductsCount();
    }

    // get products based on category
    @GetMapping("products/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable Category category){
        return productService.getProductsByCategory(category);
    }

    // get count by catogery
    @GetMapping("products/count/{category}")
    public Integer getProductsCountByCategory(@PathVariable Category category){
        return productService.getProductsCountByCategory(category);
    }

    // get products in a particular price range and category
    @GetMapping("products/low/{low}/high/{high}/{category}")
    public List<Product> getProductsInPriceRangeAndCategory(@PathVariable double low,@PathVariable double high,@PathVariable Category category ){
        return productService.getProductsInPriceRangeAndCategory(low,high,category);
    }

    // get prodict by name
    @GetMapping("products/name/{name}")
    public  List<Product> getProductById(@PathVariable String name){
        return productService.getProductByName(name);
    }


    //POST

    // add single product
    @PostMapping("product")
    public String addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return "added";
    }

    // add multiple products
    @PostMapping("products")
    public String addProducts(@RequestBody List<Product> products){
        productService.addProducts(products);
        return "added" + products.size() + "products";
    }

    //PUT




    //DElETE

    // remove single product
    @DeleteMapping("product/id/{id}")
    public String deleteProduct(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }

    // remove muliple products
    @DeleteMapping("products/ids")
    public String deleteProduct(@RequestBody List<Integer>  idList){
        return productService.deleteProducts(idList);
    }

    // remove all products based on category

    @DeleteMapping("products/{category}")
    public String deleteAllProductByCategory(@PathVariable Category category){
        return productService.deleteAllProductsByCategory(category);
    }

}
