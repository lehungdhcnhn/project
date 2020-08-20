package com.example.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.example.converter.CategoryConverter;
import com.example.dto.CategoryDTO;
import com.example.entities.Category;
import com.example.repositories.CategoryRepository;
import com.example.service.ICategoryService;

@Service

public class CategoryService implements ICategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryConverter categoryConverter;
	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}
	@Override
	public void saveCategory(Category category) {
		categoryRepository.save(category);
	}
	@Override
	public Category getCategoryById(long id) {
		Optional<Category> optional =categoryRepository.findById(id);
		Category category =null;
		if(optional.isPresent())
		{
			category=optional.get();
		}
		else
		{
			throw new RuntimeException("Category not found");
		}
		return category;
	}
	@Override
	public void deleteCategoryById(long id) {
		categoryRepository.deleteById(id);
	}
	@SuppressWarnings("deprecation")
	@Override
	public Slice<CategoryDTO> findAll(int page, int size) {
		Slice<Category> categorySlice=categoryRepository.findAll(new PageRequest(page, size));
		Slice<CategoryDTO> categorySliceDTO = categorySlice.map(new Function<Category,CategoryDTO >() {

			@Override
			public CategoryDTO apply(Category t) {
				CategoryDTO categoryDTO = categoryConverter.convertToCatgoryDTO(t);
				return categoryDTO;
			}
		});
		return categorySliceDTO;
	}
	@Override
	public long getNumOfCategory() {
		return categoryRepository.count();
	}
	@Override
	public boolean hasCategoryById(long Id) {
		return categoryRepository.existsById(Id);
	}
	
	
}
