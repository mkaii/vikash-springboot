package com.vikas.roomapp.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rooms")
public class Room {

    @Id
    private Integer roomId;
    private Integer roomNumber;


    private Type roomType;

    @Column(name = "price")
    private double roomPrice;

    boolean roomAvailable;
}
