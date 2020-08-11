package com.example.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.entities.Room;

public class RoomDTO {
	
	
	private String name;
	private Long id;
	private List<Room> listRoom = new ArrayList<>();
	public RoomDTO(List<Room> listRoom) {
		super();
		this.listRoom = listRoom;
	}
	public List<Room> getListRoom() {
		return listRoom;
	}
	public void setListRoom(List<Room> listRoom) {
		this.listRoom = listRoom;
	}
	private List<RoomDTO> listResult = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<RoomDTO> getListResult() {
		return listResult;
	}
	public void setListResult(List<RoomDTO> listResult) {
		this.listResult = listResult;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RoomDTO() {}
}

