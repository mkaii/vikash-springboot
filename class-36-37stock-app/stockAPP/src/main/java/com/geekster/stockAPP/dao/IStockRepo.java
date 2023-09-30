package com.geekster.stockAPP.dao;

import com.geekster.stockAPP.model.Stock;
import com.geekster.stockAPP.model.Type;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface IStockRepo extends CrudRepository<Stock,Long> {
    List<Stock> findByStockType(Type type);
    List<Stock> findByStockTypeAndStockPriceLessThanEqual(Type type, double price);

    List<Stock> findByStockName(String stockName);
    List<Stock> findByStockOwnerCountGreaterThanAndStockPriceLessThanEqual(Integer count,double price);

    List<Stock> findByStockOwnerCountGreaterThanEqualAndStockTypeOrderByStockCreationTimeStamp(int count, Type type);


    List<Stock> findByStockCreationTimeStampGreaterThanOrderByStockPriceDesc(LocalDateTime time);
}
