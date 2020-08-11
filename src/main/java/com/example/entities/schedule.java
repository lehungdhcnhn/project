package com.example.entities;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="schedule")
public class schedule {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="time")
	@DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
	private Date time;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Movie_id")
    private Movie movie;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Room_id")
    private Room room;
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	
	
}
