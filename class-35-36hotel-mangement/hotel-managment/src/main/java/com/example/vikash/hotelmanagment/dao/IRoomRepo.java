package com.example.vikash.hotelmanagment.dao;

import com.example.vikash.hotelmanagment.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomRepo extends CrudRepository<Room,Integer> {
}
