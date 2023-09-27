package com.vikas.roomapp.service;


import com.vikas.roomapp.dao.IRoomRepo;
import com.vikas.roomapp.model.Room;
import com.vikas.roomapp.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    IRoomRepo roomRepo;


    public String addRooms(List<Room> newRooms) {

        roomRepo.saveAll(newRooms);
        return "added";
    }

    public List<Room> getAll() {
        return (List<Room>) roomRepo.findAll();
    }

    public String removeAll() {

        roomRepo.deleteAll();
        return "cleared";
    }

    public List<Room> getAllAvailableRooms() {

        return roomRepo.findByRoomAvailable(true);
    }

    public List<Room> getAllAvailableRoomsByType(Type type) {

        return roomRepo.findByRoomAvailableAndRoomType(true,type);
    }

    public List<Room> getAllLessEqualPriceRoomsByType(double price, Type type) {

        return roomRepo.findByRoomPriceLessThanEqualAndRoomType(price,type);
    }
}
