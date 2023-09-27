package com.geekster.stockAPP.service;

import com.geekster.stockAPP.dao.IStockRepo;
import com.geekster.stockAPP.model.Stock;
import com.geekster.stockAPP.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StockService {

    @Autowired
    IStockRepo stockRepo;

    public String addStocks(List<Stock> myStocks) {

        stockRepo.saveAll(myStocks);
        return "added";
    }

    public Stock addStock(Stock stock) {
       return stockRepo.save(stock);

    }

    public List<Stock> getAllStocks() {
        return (List<Stock>) stockRepo.findAll();
    }

    public List<Stock> getStocksById(List<Long> ids) {
        return (List<Stock>) stockRepo.findAllById(ids);
    }

    public boolean ifStockExist(Long id) {
        return stockRepo.existsById(id);
    }

    public List<Stock> stocksByType(Type type) {

        return  stockRepo.findByStockType(type);
    }

    public List<Stock> stocksByTypeAndLessThenEqualPrice(Type type, double price) {
        return stockRepo.findByStockTypeAndStockPriceLessThanEqual(type,price);
    }

    public Integer countStocksByName(String stockName) {
       return stockRepo.findByStockName(stockName).size();
    }  /// we get back ti this one

    public String removeAllStocks() {
        stockRepo.deleteAll();
        return "all stocks where removed";
    }

    public String removeStockById(Long id) {
        stockRepo.deleteById(id);
        return "stock was removed";
    }

    public String removeStocksByIds(List<Long> ids) {
        stockRepo.deleteAllById(ids);
        return "stocks with given ids where removed";

    }

  /*  public void updateById(long id, Stock updatedStock) {
      Stock initialStock = stockRepo.findById(id);
        if(initialStock != null){
            initialStock.getStockName().updatedStock().setStockName());
        }*/



/*    public void updateById(long id, Stock updatedStock) {
        Optional<Stock> optStock = stockRepo.findById(id);
        Stock myStock = optStock.get();




    }*/

 /*   public List<Stock> getPriceByName(String name) {
        return stockRepo.findByStockName(name);
    }*/

    public Stock getStockById(Long id) {
        return stockRepo.findById(id).get();
    }

 /*   public List<Stock> removestocksByNames(String name) {
        return stockRepo.
    }*/
}












/*

    public Stock getStockById(Long id) {
        Stock stock = stockRepo.findById(id);

        if (stock != null) {
            return stock;
        } else {
            return null; // Return null if the stock is not found
        }
    }
*/




