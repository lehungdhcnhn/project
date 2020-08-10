package com.example.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Transient;

import com.example.entities.Category;

public class MovieDTO extends AbstractModel {
	private String content;
	private String sDescription;
	private String title;
	private Long id;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	private List<CategoryDTO> listResult = new ArrayList<>();
	private List <MovieDTO> listResultMovie = new ArrayList<MovieDTO>();
	
	public List<MovieDTO> getListResultMovie() {
		return listResultMovie;
	}
	public void setListResultMovie(List<MovieDTO> listResultMovie) {
		this.listResultMovie = listResultMovie;
	}
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
	public MovieDTO() {}

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
	@Transient
	public String getThumbnailImagePath()
	{
		if(thumbnail==null||id==null) return null;
		return "/thumbnail-pictures/"+id+"/"+thumbnail;
	}
}
