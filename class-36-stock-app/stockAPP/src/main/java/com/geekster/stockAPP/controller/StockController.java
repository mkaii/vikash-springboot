package com.geekster.stockAPP.controller;

import com.geekster.stockAPP.model.Stock;
import com.geekster.stockAPP.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StockController {

    @Autowired
    StockService stockService;

    @PostMapping("stocks")
    public String addStocks(@RequestBody List<Stock> myStocks)
    {
        return stockService.addStocks(myStocks);
    }

    @GetMapping("stocks")
    public List<Stock> getAllStocks()
    {
        return stockService.getAllStocks();
    }

    //removeStockById

    //updateStockById

    //getStocksByTypeLessEqualPrice

    //try all crud methods 

    //more custom finders
}
