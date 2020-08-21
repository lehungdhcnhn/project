package com.example.service;

import java.util.List;

import org.springframework.data.domain.Slice;

import com.example.dto.RoomDTO;
import com.example.entities.Room;

public interface IRoomService {
	List<Room> getAllRoom();
	Room getRoombyId(long roomId);
	void saveRoom(Room room);
	void deleteRoombyId(long roomId);
	long getNumOfRoom();
	Slice<Room>  findAll(int page, int size);
	boolean hasRoomById(long Id);
}
