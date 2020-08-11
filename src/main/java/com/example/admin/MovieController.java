package com.example.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.MovieDTO;
import com.example.entities.Movie;
import com.example.service.ICategoryService;
import com.example.service.IMovieService;

@Controller
public class MovieController {
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IMovieService movieService;

	@RequestMapping(value="/admin/listMovie",method = RequestMethod.GET)
	public String viewHomePage(Model model) {
		MovieDTO dto = new MovieDTO();
		dto.setListResultMovie(movieService.getAll());
		model.addAttribute("listMovie", dto);
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
	///////////////////////
	@RequestMapping(value = "/multipleMovieChange", method = RequestMethod.POST,params = "action=deleteMovieList" )
	public String deleteRoomList(@RequestParam("movieId") Long[] movieListId) {
		for(long movieId : movieListId) {
			movieService.deleleFile(movieId);
			movieService.delete(movieId);	
		}
		return "redirect:/admin/listMovie";
	}

	@RequestMapping(value = "/multipleMovieChange" , method = RequestMethod.POST , params = "action=updateMovieList")
	public String viewUpdateRoomList(@RequestParam("movieId") long[] listMovieId ,Model model) {
		MovieDTO movieDTO = new MovieDTO();
		List<Movie> movies = new ArrayList<>();
		for(long roomId : listMovieId) {
			movies.add(movieService.findById(roomId));
		}
		movieDTO.setListMovieEntity(movies);
		model.addAttribute("listMovie", movieDTO);
		return "movie/updateListMovie";
	}

	@RequestMapping(value = "/saveListMovie", method =RequestMethod.POST)
	public String saveListRoom(@ModelAttribute("listMovie") MovieDTO movieDTO,@RequestParam("fileImage") MultipartFile multipartFile,@PathVariable(value = "id") long[] ids) throws IOException {
		for(Movie movie : movieDTO.getListMovieEntity()) {
			for(long id : ids)
			{
				movieService.deleleFile(id);
			}
			String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
			movieService.saveFile(movie, fileName, multipartFile);
		}
		return "redirect:admin/listRoom";

	}
}
