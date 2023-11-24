package com.vikas.roomapp.dao;

import com.vikas.roomapp.model.Room;
import com.vikas.roomapp.model.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IRoomRepo extends CrudRepository<Room,Integer> {

    List<Room> findByRoomAvailable(boolean b);

    List<Room> findByRoomAvailableAndRoomType(boolean e, Type type1);

    List<Room> findByRoomPriceLessThanEqualAndRoomType(double price, Type type);
}
