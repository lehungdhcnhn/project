package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.entities.Category;

public interface ICategoryService {
	List<Category> getAllCategory();
	void saveCategory(Category category);
	Category getCategoryById(long id);
	void deleteCategoryById(long id);
	
}
