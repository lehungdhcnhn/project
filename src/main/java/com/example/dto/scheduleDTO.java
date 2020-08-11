package com.example.dto;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class scheduleDTO {
	private Long id;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
	private Date time;
	
	private Long movieId;
	private Long roomId;
	private List<scheduleDTO> listResult;
	private String movieName;
	private String roomName;

	public String getMovieName() {
		return movieName;
	}

	
	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
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
