package com.example.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.entities.schedule;

public class scheduleDTO extends AbstractModel {
	private Long id;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
	private Date time;
	
	private Long movieId;
	private Long roomId;
	@Valid
	private List<scheduleDTO> listResult;
	private String movieName;
	private String roomName;
	private List<schedule> listScheduleEntity= new ArrayList<>();
	public String getMovieName() {
		return movieName;
	}
	public List<schedule> getListScheduleEntity() {
		return listScheduleEntity;
	}
	public void setListScheduleEntity(List<schedule> listScheduleEntity) {
		this.listScheduleEntity = listScheduleEntity;
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
