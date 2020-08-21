package com.example.service;

import java.util.List;

import org.springframework.data.domain.Slice;

import com.example.dto.CategoryDTO;
import com.example.entities.Category;

public interface ICategoryService {
	List<Category> getAllCategory();
	void saveCategory(Category category);
	Category getCategoryById(long id);
	void deleteCategoryById(long id);
	Slice<CategoryDTO>  findAll(int page, int size);
	long getNumOfCategory();
	boolean hasCategoryById(long Id);
}
