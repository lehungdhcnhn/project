package com.example.service;

import java.util.List;

import com.example.entities.Room;

public interface IRoomService {
	List<Room> getAllRoom();
	Room getRoombyId(long roomId);
	void saveRoom(Room room);
	void deleteRoombyId(long roomId);
}
