package com.geekster.stockAPP.service;

import com.geekster.stockAPP.dao.IStockRepo;
import com.geekster.stockAPP.model.Stock;
import com.geekster.stockAPP.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD:class-36-37-38-39stock-app/stockAPP/src/main/java/com/geekster/stockAPP/service/StockService.java
=======
import org.springframework.cglib.core.Local;
import org.springframework.data.crossstore.ChangeSetPersister;
>>>>>>> 808aab6db72458cc82266f9ae40cb0a74ea08cb6:class-36-37stock-app/stockAPP/src/main/java/com/geekster/stockAPP/service/StockService.java
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class StockService {

    @Autowired
    IStockRepo stockRepo;

    // post

    public String addStocks(List<Stock> myStocks) {

        stockRepo.saveAll(myStocks);
        return "added";
    }

    public Stock addStock(Stock stock) {
       return stockRepo.save(stock);

    }

    // get
    public List<Stock> getAllStocks() {
        return (List<Stock>) stockRepo.findAll();
    }

    public List<Stock> getStocksById(List<Long> ids) {

        return (List<Stock>) stockRepo.findAllById(ids);
    }

    public boolean isStockExist(Long id) {
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
    }

    public Stock getStockById(Long id) {

        return stockRepo.findById(id).get(); // is how you use instead of optional ¨¨
    }
<<<<<<< HEAD:class-36-37-38-39stock-app/stockAPP/src/main/java/com/geekster/stockAPP/service/StockService.java
=======

    public List<Stock> getStocksGreaterCountLessThanEqualPrice(Integer count, double price) {
>>>>>>> 808aab6db72458cc82266f9ae40cb0a74ea08cb6:class-36-37stock-app/stockAPP/src/main/java/com/geekster/stockAPP/service/StockService.java


    public List<Stock> stocksByCountGreaterThanEqualAndLessThenEqualPrice(Integer count, double price) {
        return stockRepo.findByStockOwnerCountGreaterThanEqualAndStockPriceLessThanEqual(count,price);
    }


    public List<Stock> stocksByGreaterThanEqualCountAndTypeSortAscend(int count, Type type) {
        return stockRepo.findByStockOwnerCountGreaterThanEqualAndStockTypeOrderByStockCreationTimeStamp(count,type);
    }

    public List<Stock> stocksByGreaterThanDateSortDescPrice(LocalDateTime date) {
        return stockRepo.findByStockCreationTimeStampGreaterThanOrderByStockPriceDesc(date);
    }

    public List<Stock> stocksNotByTypeAndNotStartDateAndNotByCountSortDescByPrice(Type type, LocalDateTime before, LocalDateTime after, Integer count) {
        return stockRepo.findByStockTypeAndStockCreationTimeStampBeforeOrStockCreationTimeStampAfterStockOwnerCountNotInOrderByDesc(type,before,after,count);
    }
    // delete
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


    public List<Stock> getAllStocksGreaterCountAndByTypeSortByTime(int count, Type type) {

        return stockRepo.findByStockOwnerCountGreaterThanEqualAndStockTypeOrderByStockCreationTimeStamp(count,type);
    }

<<<<<<< HEAD:class-36-37-38-39stock-app/stockAPP/src/main/java/com/geekster/stockAPP/service/StockService.java
    }

=======
    public List<Stock> getAllStocksGreaterTimeSortByPriceDesc(LocalDateTime time) {

        return stockRepo.findByStockCreationTimeStampGreaterThanOrderByStockPriceDesc(time);
    }

    public List<Stock> getNotTypeNotLikeThisPatternNotInCountsSortedPriceDesc(Type type, LocalDateTime before, LocalDateTime after, List<Integer> invalidOwnerCounts) {

        //return stockRepo.findByStockTypeNotAndStockCreationTimeStampNotLikeAndStockOwnerCountNotInOrderByStockPriceDesc(type,datePattern,invalidOwnerCounts);
        return stockRepo.findByStockTypeNotAndStockCreationTimeStampBeforeOrStockCreationTimeStampAfterAndStockOwnerCountNotInOrderByStockPriceDesc(type,before,after,invalidOwnerCounts);

    }
}
>>>>>>> 808aab6db72458cc82266f9ae40cb0a74ea08cb6:class-36-37stock-app/stockAPP/src/main/java/com/geekster/stockAPP/service/StockService.java


















