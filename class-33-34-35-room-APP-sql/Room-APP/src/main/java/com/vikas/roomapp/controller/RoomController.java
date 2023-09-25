package com.vikas.roomapp.controller;

import com.vikas.roomapp.model.Room;
import com.vikas.roomapp.model.Type;
import com.vikas.roomapp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {


    @Autowired
    RoomService roomService;

    @PostMapping("rooms")
    public String addRooms(@RequestBody List<Room> newRooms)
    {
        return roomService.addRooms(newRooms);
    }

    @GetMapping("rooms")
    public List<Room> getAllRooms()
    {
        return roomService.getAll();
    }

    @GetMapping("rooms/available")
    public List<Room> getAllAvailableRooms()
    {
        return roomService.getAllAvailableRooms();
    }

    @GetMapping("rooms/available/type/{type}")
    public List<Room> getAllAvailableRoomsByType(@PathVariable Type type)
    {
        return roomService.getAllAvailableRoomsByType(type);
    }

    @GetMapping("rooms/price/lessEqual/{price}/type/{type}")
    public List<Room> getAllLessEqualPriceRoomsByType(@PathVariable double price,@PathVariable Type type)
    {
        return roomService.getAllLessEqualPriceRoomsByType(price,type);
    }


    @DeleteMapping("rooms")
    public String deleteAllRooms()
    {
        return roomService.removeAll();
    }


}
