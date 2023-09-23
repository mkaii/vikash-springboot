package com.example.vikash.hotelmanagment.service;


import com.example.vikash.hotelmanagment.dao.IRoomRepo;
import com.example.vikash.hotelmanagment.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    IRoomRepo IRoomRepo;

    public String addRooms(List<Room> newRooms) {
        IRoomRepo.saveAll(newRooms);

        return "rooms added";
    }

    public String addRoom(Room newRoom) {
        IRoomRepo.save(newRoom);
        return "room added";
    }

    public List<Room> getAllRooms() {
         return (List<Room>) IRoomRepo.findAll();
    }

    public String removeAllRooms() {
        IRoomRepo.deleteAll();
        return "all rooms removed";
    }


    public Optional<Room> getRoomById(Integer id) {
       return IRoomRepo.findById(id);
    }

    public List<Room> getRoomsById(List<Integer> ids) {
        return (List<Room>) IRoomRepo.findAllById(ids);
    }

    public boolean IfRoomExist(Integer id) {
        return IRoomRepo.existsById(id);
    }

    public String removeRoomById(Integer id) {
        IRoomRepo.deleteById(id);

        return id + "delted";
    }

    public String removeRoomsByIds(List<Integer> ids) {
        IRoomRepo.deleteAllById(ids);

        return "rooms with given ids where removed";
    }
}
