package com.geekster.stockAPP.dao;

import com.geekster.stockAPP.model.Stock;
import org.springframework.data.repository.CrudRepository;


public interface IStockRepo extends CrudRepository<Stock,Long> {
}
