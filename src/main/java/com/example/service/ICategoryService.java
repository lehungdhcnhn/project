package com.example.service;

import java.util.List;

import com.example.entities.Category;

public interface ICategoryService {
	List<Category> getAllCategory();
	void saveCategory(Category category);
	Category getCategoryById(long id);
	void deleteCategoryById(long id);
	
}
