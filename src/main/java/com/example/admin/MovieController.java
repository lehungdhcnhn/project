package com.example.admin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String viewHomePage(Model model) {
		
		model.addAttribute("listMovie", movieService.getAll());
		return "Movie/listMovie";
	}

	@GetMapping("/admin/editMovie")
	public String viewEditPage(Model model) {
		model.addAttribute("listCategory", categoryService.getAllCategory());
		MovieDTO movie = new MovieDTO();
		model.addAttribute("movie", movie);
		return "Movie/editMovie";
	}

	
	@PostMapping("/saveMovie")
	public String saveMovie(@ModelAttribute("movie") Movie movie,@RequestParam("fileImage") MultipartFile multipartFile) throws IOException
	{
		String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
		movieService.saveFile(movie, fileName, multipartFile);
		return "redirect:/admin/listMovie";
	}

	@GetMapping("/updateMovie/{id}")
	public String updateMive(@PathVariable(value = "id") long id, Model model) {
		model.addAttribute("listCategory", categoryService.getAllCategory());
		Movie movie = movieService.findById(id);
		model.addAttribute("movie", movie);
		return "Movie/updateMovie";
	}

	@GetMapping("/deleteMovie/{id}")
	public String deleteMovie(@PathVariable(value = "id") long id){
		movieService.deleleFile(id);
		movieService.delete(id);
		return "redirect:/admin/listMovie";
	}
	@PostMapping("/updateMovieLate/{id}")
	public String updateMovie(@ModelAttribute("movie") Movie movie,@RequestParam("fileImage") MultipartFile multipartFile,@PathVariable(value = "id") long id) throws IOException
	{
		movieService.deleleFile(id);
		String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		movieService.saveFile(movie, fileName, multipartFile);
		return "redirect:/admin/listMovie";
	}
}
