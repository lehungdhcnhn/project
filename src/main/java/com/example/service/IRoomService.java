package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import com.example.entities.Room;

public interface IRoomService {
	List<Room> getAllRoom();
	Room getRoombyId(long roomId);
	void saveRoom(Room room);
	void deleteRoombyId(long roomId);
	Slice<Room>  findAll(int page, int size);
	long numOfRoom();
	boolean hasRoomById(long Id);
}
