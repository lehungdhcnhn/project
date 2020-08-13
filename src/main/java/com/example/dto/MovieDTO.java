package com.example.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;
import javax.validation.Valid;

import com.example.entities.Movie;

public class MovieDTO extends AbstractModel {
	private String content;
	private String sDescription;
	private String title;
	private Long id;
	private String name;
	private String thumbnail;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Valid
	private List<Movie> listMovieEntity = new ArrayList<>();
	private List<CategoryDTO> categories = new ArrayList<>();
	private List <MovieDTO> listResultMovie = new ArrayList<MovieDTO>();
	
	public MovieDTO() {
		super();
	}
	
	public List<Movie> getListMovieEntity() {
		return listMovieEntity;
	}
	public void setListMovieEntity(List<Movie> listMovieEntity) {
		this.listMovieEntity = listMovieEntity;
	}
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
	
	public List<CategoryDTO> getCategories() {
		return categories;
	}
	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}
	@Transient
	public String getThumbnailImagePath()
	{
		if(thumbnail==null||id==null) return null;
		return "/thumbnail-pictures/"+id+"/"+thumbnail;
	}
}
