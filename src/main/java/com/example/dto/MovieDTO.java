package com.example.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.entities.Category;

public class MovieDTO extends AbstractDTO<MovieDTO> {
	private String content;
	private String sDescription;
	private String title;
	private Set<MovieDTO> listResult = new HashSet<MovieDTO>();
	
	public MovieDTO() {}
	public void setListResult(Set<MovieDTO> listResult) {
		this.listResult = listResult;
	}
	private Set<CategoryDTO> categories = new HashSet<>();
	
	public Set<CategoryDTO> getCategories() {
		return categories;
	}
	public void setCategories(Set<CategoryDTO> categories) {
		this.categories = categories;
	}
	private String thumbnail;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	public String getsDescription() {
		return sDescription;
	}
	public void setsDescription(String sDescription) {
		this.sDescription = sDescription;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
}
