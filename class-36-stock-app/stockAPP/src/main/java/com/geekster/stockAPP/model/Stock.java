package com.geekster.stockAPP.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //unique
    private Long stockId;

    private String stockName;

    private double stockPrice;

    private int stockOwnerCount;

    private Type stockType;

    private LocalDateTime stockCreationTimeStamp;
}
