package com.example.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {
	

	private String name;
	
	
	private Long id;
	private List<CategoryDTO> listResult = new ArrayList<>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<CategoryDTO> getListResult() {
		return listResult;
	}
	public void setListResult(List<CategoryDTO> listResult) {
		this.listResult = listResult;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CategoryDTO() {}
}

