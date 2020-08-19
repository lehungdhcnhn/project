package com.example.service;

import java.util.List;

import org.springframework.data.domain.Slice;

import com.example.entities.Category;

public interface ICategoryService {
	List<Category> getAllCategory();
	void saveCategory(Category category);
	Category getCategoryById(long id);
	void deleteCategoryById(long id);
	long numOfCategory();
	Slice<Category> findAll(int i, int pageSize);
	
}
