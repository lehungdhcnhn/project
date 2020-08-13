package com.example.admin;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.CategoryDTO;
import com.example.entities.Category;
import com.example.service.ICategoryService;

@Controller
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	@GetMapping("/admin/listCategory")
	public String viewHomePage(Model model)
	{
		model.addAttribute("listCategory",categoryService.getAllCategory());
		return "category/listCategory";
	}
	@GetMapping("/admin/editCategory")
	public String showNewCategory(Model model)
	{
		Category category = new Category();
		model.addAttribute("category",category);
		return "category/editCategory";
	}
	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute("category") Category category,  @Valid Category categoryValid, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) {
			return "category/editCategory";
		}
		categoryService.saveCategory(category);
		return "redirect:/admin/listCategory";
	}
	@GetMapping("/updateCategory/{id}")
	public String updateCategory(@PathVariable(value="id") long id,Model model )
	{
		Category category =categoryService.getCategoryById(id);
		model.addAttribute("category",category);
		return "Category/updateCategory";
	}
	@GetMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable(value="id") long id )
	{
		categoryService.deleteCategoryById(id);
		return "redirect:/admin/listCategory";
	}
	
	@RequestMapping(value = "/multipleCategoryChange", method =RequestMethod.POST, params = "action=deleteListCategory" )
	public String deleteListCategory(@RequestParam("categoryId") long[] listCategoryId) {
		for(long categoryId : listCategoryId ) {
			categoryService.deleteCategoryById(categoryId);
		}
		return "redirect:/admin/listCategory";
	}
	
	@RequestMapping(value = "/multipleCategoryChange", method = RequestMethod.POST, params = "action=updateListCategory")
	public String updateListCategory(@RequestParam("categoryId") long[] listCategoryId, Model model) {
		CategoryDTO categoryDTO = new CategoryDTO();
		List<Category> listCategory = new ArrayList<>();
		for(long categoryId : listCategoryId) {
			listCategory.add(categoryService.getCategoryById(categoryId));
		}
		categoryDTO.setListCategory(listCategory);
		model.addAttribute("listCategory", categoryDTO);
		
		return "category/updateListCategory";
		
	}
	
	@RequestMapping(value = "/saveListCategory", method = RequestMethod.POST)
	public String saveListCategory(@Valid @ModelAttribute("listCategory") CategoryDTO categoryDTO, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "category/updateListCategory";
		}
		for(Category category : categoryDTO.getListCategory()) {
			categoryService.saveCategory(category);
		}
		return "redirect:/admin/listCategory";
	}
	
}
