package com.example.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.example.dto.RoomDTO;
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
		if(!optional.isPresent()) {
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
		roomRepository.deleteById(roomId);
	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public Slice<RoomDTO> findAll(int page, int size) {
		Slice<Room> room = roomRepository.findAll(new PageRequest(page, size));
		Slice<RoomDTO> roomDTO = room.map(new Function<Room, RoomDTO>() {
			@Override
			public RoomDTO apply(Room t) {
				RoomDTO dto = new RoomDTO();
				dto.setId(t.getId());
				dto.setCode(t.getCode());
				dto.setName(t.getName());
				return dto;
			}
		});
		
		return roomDTO;
	}

	@Override
	public long getNumOfRoom() {
		// TODO Auto-generated method stub
		return roomRepository.count();
	}

	@Override
	public boolean hasRoomById(long Id) {
		// TODO Auto-generated method stub
		return roomRepository.existsById(Id);
	}
	
}
