package com.example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.dto.RoomDTO;
import com.example.entities.Room;

@Component
public class RoomConverter {
	static ModelMapper modelMapper = new ModelMapper();
	public RoomDTO convertToRoomDTO(Room room)
	{
		RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
		//movieDTO.setCategoryCode(movie.getCategory().getCode());
		return roomDTO;
	}
	public Room convertToRoomEntity(RoomDTO roomDTO)
	{
		Room room = modelMapper.map(roomDTO, Room.class);
		return room;
	}
}
