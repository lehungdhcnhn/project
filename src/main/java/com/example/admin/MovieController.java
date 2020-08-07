package com.example.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dto.MovieDTO;
import com.example.entities.Category;
import com.example.entities.Movie;
import com.example.service.ICategoryService;
import com.example.service.IMovieService;

@Controller
public class MovieController {
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IMovieService movieService;
	@GetMapping("/admin/listMovie")
	public String viewHomePage(Model model)
	{
		MovieDTO dto = new MovieDTO();
		dto.setListResult(movieService.getAll());
		model.addAttribute("listMovie",dto);
		return "Movie/listMovie";
	}
	
	@GetMapping("/admin/editMovie")
	public String viewEditPage(Model model)
	{
		model.addAttribute("listCategory",categoryService.getAllCategory());
		MovieDTO movie = new MovieDTO();
		model.addAttribute("movie",movie);
		return "Movie/editMovie";
	}
	@GetMapping("/admin/updateMovie")
	public String viewUpdatePage(Model model)
	{
		model.addAttribute("listCategory",categoryService.getAllCategory());
		return "Movie/updateMovie";
	}
	@PostMapping("/saveMovie")
	public String saveMovie(@ModelAttribute("movie") Movie movie)
	{
		long id =movieService.create(movie);
		return "redirect:/admin/listMovie";
	}
	@GetMapping("/updateMovie/{id}")
	public String updateCategory(@PathVariable(value="id") long id,Model model )
	{
		model.addAttribute("listCategory",categoryService.getAllCategory());
		Movie movie =movieService.findById(id);
		model.addAttribute("movie",movie);
		return "Movie/updateMovie";
	}
	@GetMapping("/deleteMovie/{id}")
	public String deleteMovie(@PathVariable(value="id") long id )
	{
		movieService.delete(id);
		return "redirect:/admin/listMovie";
	}
}
