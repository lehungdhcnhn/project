package com.example.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Room;
import com.example.repositories.RoomRepository;
import com.example.service.IRoomService;
@Service
public class RoomService implements IRoomService {
	
	@Autowired
	RoomRepository roomRepository;

	@Override
	public List<Room> getAllRoom() {
		
		return roomRepository.findAll();
	}

	@Override
	public Room getRoombyId(long roomId) {
		
		Optional<Room> optional = roomRepository.findById(roomId);
		Room room = null;
		if(optional.isEmpty()) {
			throw new RuntimeException("Khong thay phong nay!");
		}
		else {
			room = optional.get();
		}
		return room;
	}

	@Override
	public void saveRoom(Room room) {
		roomRepository.save(room);
	}

	@Override
	public void deleteRoombyId(long roomId) {
		// TODO Auto-generated method stub
		roomRepository.deleteById(roomId);
	}
	
}
