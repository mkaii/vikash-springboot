package com.vikas.ecommerceapp.service;


import com.vikas.ecommerceapp.dao.ProductRepo;
import com.vikas.ecommerceapp.model.Category;
import com.vikas.ecommerceapp.model.PriceStrategy;
import com.vikas.ecommerceapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Map<Integer, Product> getAllProducts() {
        return productRepo.getProducts();
    }

    public void addProduct(Product product) {

        getAllProducts().put(product.getId(), product);
    }

    public Product getProductById(Integer id) {

        for (Integer key : getAllProducts().keySet()) {
            if (key.equals(id)) {
                return getAllProducts().get(key);
            }
        }

        return null;
    }

    public void addProducts(List<Product> products) {

        Map<Integer, Product> existingProducts = getAllProducts();
        for (Product product : products) {
            existingProducts.put(product.getId(), product);
        }
    }

    public List<Product> getProductsInPriceRange(double lowerPrice, double higherPrice) {

        if (lowerPrice > higherPrice) {
            throw new IllegalStateException("Price range Invalid");
        }
        List<Product> priceFilteredProducts = new ArrayList<>();
        Map<Integer, Product> allProducts = getAllProducts();

        for (Integer key : allProducts.keySet()) {
            double productPrice = allProducts.get(key).getPrice();
            if (productPrice >= lowerPrice && productPrice <= higherPrice) {
                priceFilteredProducts.add(allProducts.get(key));
            }
        }

        return priceFilteredProducts;
    }

    public Integer getProductCount() {

        return getAllProducts().size();
    }

    public List<Product> getProductByCategory(Category category) {

        Map<Integer, Product> allProducts = getAllProducts();

        List<Product> categoryBasedProducts = new ArrayList<>();
        for (Integer key : allProducts.keySet()) {
            Product product = allProducts.get(key);

            if (product.getCategory().equals(category)) {
                categoryBasedProducts.add(product);
            }
        }

        return categoryBasedProducts;
    }

    public long getProductCountByCategory(Category myCategory) {

        return getAllProducts().entrySet().
                stream().
                filter(product -> product.getValue().getCategory().equals(myCategory))
                .count();
    }

    public List<Product> getProductsInPriceRangeByCategory(double lowerPrice, double higherPrice, Category category) {

      /*  List<Product> priceFilteredList =  getProductsInPriceRange(lowerPrice,higherPrice);

        List<Product> priceAndCategoryFilteredList = new ArrayList<>();

        for(Product p : priceFilteredList)
        {
            if(p.getCategory().equals(category))
            {
                priceAndCategoryFilteredList.add(p);
            }
        }

        return priceAndCategoryFilteredList;*/

        return getAllProducts().values().
                stream().filter(product ->
                        product.getPrice() >= lowerPrice
                                && product.getPrice() <= higherPrice
                                && product.getCategory().equals(category)).
                collect(Collectors.toList());

    }


    public String deleteAllProductsByCategory(Category category) {
        List<Integer> keysToRemove = new ArrayList<>();  // keys of the products that needs to be removed

        for (Integer key : getAllProducts().keySet()) {   // iterating to the hashmap to
            if (getAllProducts().get(key).getCategory().equals(category)) { // checking if condition is true
                keysToRemove.add(key);  // adding those to keys to remove
            }
        }
        for (Integer key : keysToRemove) {  // iterating trough keys to remove
            getAllProducts().remove(key); // removing products from hahsmap where catogry matches which the category we pass
        }
        return category + "products where removed";
    }

    public String deleteProducts(List<Integer> idList) {
        int counter = 0;
        for (Integer key : idList) {
            for (Product product : getAllProducts().values()) {
                if (key.equals(product.getId())) {
                    getAllProducts().values().remove(product);
                    counter++;
                    break;
                }
            }
        }
        return counter + "rodects where removed";

    }


    public String deleteProduct(Integer id) {
        for (Integer key : getAllProducts().keySet()) {

            if (getAllProducts().get(key).getId().equals(id)) {
                getAllProducts().remove(key);
                return "todo removed";
            }
        }
        return "todo not found ";


    }

    public String  updatePriceBYCategory(Category category, PriceStrategy strategy, float percentage) {

        int polarity = (strategy == PriceStrategy.INCREASE) ? 1:-1;

        getProductByCategory(category).
                forEach(product -> {
                    product.setPrice(
                            product.getPrice() *
                                    (100 + (polarity * percentage))
                                    /100);
                });


        return "price updated";

    }
}