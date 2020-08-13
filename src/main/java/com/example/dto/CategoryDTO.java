package com.example.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.entities.Category;

public class CategoryDTO {
	

	private String name;
	
	
	private Long id;
	
	@Valid
	private List<Category> listCategory = new ArrayList<>();
	public CategoryDTO(List<Category> listCategory) {
		super();
		this.listCategory = listCategory;
	}
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
	public List<Category> getListCategory() {
		return listCategory;
	}
	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}
}

