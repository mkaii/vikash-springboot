package com.geekster.stockAPP.service;

import com.geekster.stockAPP.dao.IStockRepo;
import com.geekster.stockAPP.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StockService {

    @Autowired
    IStockRepo stockRepo;

    public String addStocks(List<Stock> myStocks) {

        stockRepo.saveAll(myStocks);
        return "added";
    }

    public List<Stock> getAllStocks() {
        return (List<Stock>) stockRepo.findAll();
    }
}
