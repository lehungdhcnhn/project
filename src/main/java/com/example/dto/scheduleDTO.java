package com.example.dto;

import java.sql.Date;
import java.util.List;

import com.example.entities.Movie;
import com.example.entities.Room;

public class scheduleDTO {
	private Long id;
	private String address;
	private Date time;
	private Long movieId;
	private Long roomId;
	private List<scheduleDTO> listResult;
	private String movieName;
	private String roomName;
	
	
	
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public List<scheduleDTO> getListResult() {
		return listResult;
	}
	public void setListResult(List<scheduleDTO> listResult) {
		this.listResult = listResult;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	
	
}
