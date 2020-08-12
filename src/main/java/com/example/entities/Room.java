package com.example.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "room")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=1,   max=10,  message = "Mã phòng từ 1-10 ký tự")
	@Column(name = "code")
	private String code;
	
	@NotNull
	@Size(min=2,  max=30, message = "Tên phòng từ 2-30 ký tự")
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "room")
	private List<schedule> schedules = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<schedule> schedules) {
		this.schedules = schedules;
	}
	
	

}
