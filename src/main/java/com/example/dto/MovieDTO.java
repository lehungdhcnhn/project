package com.example.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;
import javax.validation.Valid;

import com.example.entities.Movie;

public class MovieDTO extends AbstractModel {
	private String content;
	private String member;
	private Long length;
	private Long id;
	private String name;
	private String thumbnail;
	private String thumbnailImagePathVer;
	private Date startTime;
	
	
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public Long getLength() {
		return length;
	}
	public void setLength(Long length) {
		this.length = length;
	}
	public String getThumbnailImagePathVer() {
		return thumbnailImagePathVer;
	}
	public void setThumbnailImagePathVer(String thumbnailImagePathVer) {
		this.thumbnailImagePathVer = thumbnailImagePathVer;
	}
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
