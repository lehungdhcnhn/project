package com.example.admin;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
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
	public String viewHomePage(Model model, @RequestParam(value = "page", required = false) String pageNumber,
			@RequestParam(value = "limit", required = false) Integer pageSize) {
		if (pageNumber != null) {
			long numOfCategory = categoryService.getNumOfCategory();
			long numOfPage = (long) ((numOfCategory-1) / pageSize + 1);
			return "redirect:/admin/Category?page=" + numOfPage + "&limit=" + pageSize;
		} else {
			return "redirect:/admin/Category?page=1&limit=4";
		}
	}
	@GetMapping("/admin/Category")
	public String getViewCategoryPage(Model model, @RequestParam(value = "page") int pageNumber,
			@RequestParam("limit") int pageSize) {

		long numOfCategory = categoryService.getNumOfCategory();
		long numOfPage = (long) ((numOfCategory-1) / pageSize + 1);
		System.err.println(pageNumber + "|" + numOfPage);
		Slice<CategoryDTO> sliceCategoryDTO = categoryService.findAll(pageNumber - 1, pageSize);
		model.addAttribute("listCategory", sliceCategoryDTO);
		model.addAttribute("numOfPage", numOfPage);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("numOfEntity", numOfCategory);
		model.addAttribute("pageSize", pageSize);
		return "Category/listCategory";
	}

	@GetMapping("/admin/editCategory")
	public String showNewCategory(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "Category/editCategory";
	}
	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute("category") Category category, @Valid Category categoryValid,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "category/editCategory";
		}
		categoryService.saveCategory(category);
		return "redirect:/admin/listCategory?page=lastPage&limit=4";
	}

	@GetMapping("/updateCategory/{id}")
	public String updateCategory(@PathVariable(value = "id") long id, Model model) {
		Category category = categoryService.getCategoryById(id);
		model.addAttribute("category", category);
		return "Category/updateCategory";
	}

	@GetMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable(value = "id") long id) {
		categoryService.deleteCategoryById(id);
		return "redirect:/admin/listCategory?success";
	}

	@RequestMapping(value = "/multipleCategoryChange", method = RequestMethod.POST)
	public String deleteListCategory(@RequestParam(value = "categoryId", required = false) long[] listCategoryId) {
		if (listCategoryId != null) {
			for (long categoryId : listCategoryId) {
				categoryService.deleteCategoryById(categoryId);
			}
			return "redirect:/admin/listCategory?success";
		} else {
			return "redirect:/admin/listCategory?error";

		}

	}

	@RequestMapping(value = "/multipleCategoryChange", method = RequestMethod.POST, params = "action=updateListCategory")
	public String updateListCategory(@RequestParam(value = "categoryId", required = false) Long[] listCategoryId,
			Model model) {

		if (listCategoryId == null) {
			return "redirect:/admin/listCategory?errorUpdate";
		} else {
			CategoryDTO categoryDTO = new CategoryDTO();
			List<Category> listCategory = new ArrayList<>();
			for (long categoryId : listCategoryId) {
				listCategory.add(categoryService.getCategoryById(categoryId));
			}
			categoryDTO.setListCategory(listCategory);
			model.addAttribute("listCategory", categoryDTO);

			return "Category/updateListCategory";
		}

//		return "redirect:/admin/listCategory";
	}

	@RequestMapping(value = "/saveListCategory", method = RequestMethod.POST)
	public String saveListCategory(@Valid @ModelAttribute("listCategory") CategoryDTO categoryDTO,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "category/updateListCategory";
		}
		for (Category category : categoryDTO.getListCategory()) {
			categoryService.saveCategory(category);
		}
		return "redirect:/admin/listCategory";
	}

}
