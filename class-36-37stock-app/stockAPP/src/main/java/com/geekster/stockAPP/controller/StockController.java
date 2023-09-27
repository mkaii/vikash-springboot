package com.geekster.stockAPP.controller;

import com.geekster.stockAPP.model.Stock;
import com.geekster.stockAPP.model.Type;
import com.geekster.stockAPP.service.StockService;

import jakarta.validation.constraints.Past;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class StockController {

    @Autowired
    StockService stockService;

    //post
    @PostMapping("stock")
    public Stock addStock(@RequestBody Stock stock){
       return stockService.addStock(stock);
    }

    @PostMapping("stocks")
    public String addStocks(@RequestBody List<Stock> myStocks)
    {
        return stockService.addStocks(myStocks);
    }

    //get

    @GetMapping("stocks")
    public List<Stock> getAllStocks()
    {
        return stockService.getAllStocks();
    }

    @GetMapping("stock/{id}")
    public Stock getStockById(@PathVariable Long id){
        return  stockService.getStockById(id);
    }

    @GetMapping("stocks/idList")
    public List<Stock> getStocksByIds(@RequestBody List<Long> ids){
        return  stockService.getStocksById(ids);
    }

    @GetMapping("Stock/exist/{id}")
    public boolean IfStockExist(@PathVariable Long id){
        return stockService.ifStockExist(id);
    }


    @GetMapping("stocks/ByType/{Type}")
    public List<Stock> stocksByType(@PathVariable Type Type){

        return stockService.stocksByType(Type);
    }

    @GetMapping("stocks/Type{Type}/belowPrice/{price}")
    public List<Stock> stocksByTypeAndLessThenEqualPrice(@PathVariable Type Type, @PathVariable double price){

        return stockService.stocksByTypeAndLessThenEqualPrice(Type,price);
    }

    @GetMapping("stocks/name/{stockName}/count")
    public Integer countStocksByName(@PathVariable String stockName){
        return stockService.countStocksByName(stockName); //
    }

  /*  @GetMapping("stocks/name/{name}")
    public List<Stock> getName(@PathVariable String name){
    return stockService.getPriceByName(name);
    }*/

    //Delete
    @DeleteMapping("stocks")
    public String removeAllStocks(){
        return stockService.removeAllStocks();
    }

    @DeleteMapping("Stock/{id}")
    public String removeStockById(@PathVariable Long id){
        return stockService.removeStockById(id);
    }

    @DeleteMapping("stocks/ids")
    public String removeStocksByIds(@RequestBody List<Long> ids){
        return stockService.removeStocksByIds(ids);
    }


 /*   @DeleteMapping("stocks/{name}")
    public List<Stock> removeStocksByName(@PathVariable String  name ){
        return stockService.removestocksByNames(name);
    }  // this wont work in this way
    
    */
    //removeStockById

    //updateStockById

    // put

   /* @PutMapping("stock")
    public Stock  updateById(@RequestBody long id, @RequestBody  Stock updatedStock ){

        stockService.updateById(id,updatedStock);

    }
*/
    //getStocksByTypeLessEqualPrice   done

    // get priceByName         doene

    // findByNameAndStockOwnerCount   done

   // delete stock by name


    //try all crud methods       done

    //more custom finders
}
