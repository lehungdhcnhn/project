package com.example.entities;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="movie")
public class Movie {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min=1,message="Nội dung từ 1 ký tự trở lên")
	@Column(name="content",columnDefinition = "TEXT")
	private String content;
	
	@NotNull
	@Size(min=1, max=255, message= "Mô tả ngắn từ 1-255 ký tự")
	@Column(name="sdescription")
	private String sDescription;
	
	@NotNull
	@Size(min=1,max=255, message="Tiêu đề từ 1-255 ký tự")
	@Column(name="title")
	private String title;
	@Column(name="thumbnail")
	private String thumbnail;
	
	@NotNull
	@Size(min=1, max=255,message="Tên phim từ 1-255 ký tự")
	@Column(name="name")
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public Movie(Long id, String content, String sDescription, String title, String thumbnail, String name,
			List<Category> categories, List<schedule> schedules) {
		super();
		this.id = id;
		this.content = content;
		this.sDescription = sDescription;
		this.title = title;
		this.thumbnail = thumbnail;
		this.name = name;
		this.categories = categories;
		this.schedules = schedules;
	}

	public Movie() {
		super();
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

	@OneToMany(mappedBy = "movie")
	private List<schedule> schedules = new ArrayList<>();
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
	@Transient
	public String getThumbnailImagePath()
	{
		if(thumbnail==null||id==null) return null;
		return "/thumbnail-pictures/"+id+"/"+thumbnail;
	}
}
