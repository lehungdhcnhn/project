package com.example.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	@Column(name="content")
	private String content;
	@Column(name="sdescription")
	private String sDescription;
	@Column(name="title")
	private String title;
	@Column(name="thumbnail")
	private String thumbnail;
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	@ManyToMany(cascade=CascadeType.MERGE)
	    @JoinTable(name = "movie_category",
	        joinColumns = @JoinColumn(name = "movie_id" , referencedColumnName="ID"),
	        inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName="ID")
	    )
	 private List<Category> categories = new ArrayList<Category>();
	
	

	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<schedule> schedules = new ArrayList<schedule>();
	
	public List<schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<schedule> schedules) {
		this.schedules = schedules;
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

}
