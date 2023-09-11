package com.vikas.ecommerceapp.service;


import com.vikas.ecommerceapp.dao.ProductRepo;
import com.vikas.ecommerceapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Map<Integer, Product> getAllProducts() {
        return productRepo.getProducts();
    }

    public void addProduct(Product product) {

        getAllProducts().put(product.getId(),product);
    }

    public Product getProductById(Integer id) {

        for(Integer key : getAllProducts().keySet())
        {
            if(key.equals(id))
            {
                return getAllProducts().get(key);
            }
        }

        return null;
    }

    public void addProducts(List<Product> products) {

        Map<Integer, Product> existingProducts = getAllProducts();
        for(Product product : products)
        {
            existingProducts.put(product.getId(),product);
        }
    }
}
