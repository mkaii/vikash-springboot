package com.example.vikash.hotelmanagment.controller;

import com.example.vikash.hotelmanagment.model.Room;
import com.example.vikash.hotelmanagment.model.Type;
import com.example.vikash.hotelmanagment.service.RoomService;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Repository
public class RoomController {
    @Autowired
    RoomService service;

// post
    @PostMapping("rooms")
    public String addRooms(@RequestBody List<Room> newRooms){
        return service.addRooms(newRooms);
    }
    @PostMapping("room")
    public String addRoom(@RequestBody Room newRoom){
        return service.addRoom(newRoom);
    }

    //get
    @GetMapping("rooms")
    public List<Room> getAllRooms(){
        return service.getAllRooms();
    }
    @GetMapping("room/{id}")
    public Optional<Room> getRoomById(@PathVariable Integer id){
        return  service.getRoomById(id);
    }

    @GetMapping("room/ids")
    public List<Room> getRoomsByIds(@RequestBody List<Integer> ids){
        return  service.getRoomsById(ids);
    }

    @GetMapping("room/exist")
    public boolean IfRoomExist(@PathVariable Integer id){
        return service.IfRoomExist(id);
    }


    @GetMapping("rooms/availableBy/type{type}")
    public List<Room> availableByType(@PathVariable Type type){

     return service.availableByType(type);
    }

    @GetMapping("rooms/available")
    public List<Room> allAvailableRooms(){
        return service.allAvailableRoooms();
    }



   @GetMapping("rooms/count")
    public Integer countRooms(){
        return service.countRooms();
    }


    //Delete
    @DeleteMapping("rooms")
    public String removeAllRooms(){
        return service.removeAllRooms();
    }

    @DeleteMapping("room/{id}")
    public String removeRoomById(@PathVariable Integer id){
        return service.removeRoomById(id);
    }

    @DeleteMapping("rooms/ids")
    public String removeRoomsByIds(@RequestBody List<Integer> ids){
        return service.removeRoomsByIds(ids);
    }



}
