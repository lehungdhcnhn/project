package com.example.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.converter.MovieConverter;
import com.example.dto.MovieDTO;
import com.example.entities.Movie;
import com.example.repositories.MovieRepository;
import com.example.service.ICategoryService;
import com.example.service.IMovieService;
import com.example.service.impl.ScheduleService;

@Controller
public class MovieControllerWeb {
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IMovieService movieService;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private MovieConverter movieConverter;
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public String viewHomePage(Model model) {
		List<Movie> movie = new ArrayList<Movie>();
		movie = movieRepository.getByMovieByDateEarly();
		List<MovieDTO> dto = new ArrayList<MovieDTO>();
		MovieDTO movieDto = new MovieDTO();
		for (Movie item : movie) {
			movieDto=movieConverter.convertToMovieDTO(item);
			movieDto.setThumbnailImagePathVer("/thumbnail-pictures/"+movieDto.getId()+"/"+movieDto.getThumbnail());
			dto.add(movieDto);
		}
		model.addAttribute("listMovie", dto);
		//diplay three item
		List<Movie> movieThree = new ArrayList<Movie>();
		movieThree = movieRepository.getByMovieByDate();
		List<MovieDTO> dtothree = new ArrayList<MovieDTO>();
		MovieDTO movieDtoThree = new MovieDTO();
		for (Movie item : movieThree) {
			movieDtoThree=movieConverter.convertToMovieDTO(item);
			movieDtoThree.setThumbnailImagePathVer("/thumbnail-pictures/"+movieDtoThree.getId()+"/"+movieDtoThree.getThumbnail());
			dtothree.add(movieDtoThree);
		}
		model.addAttribute("movieDtoThree", dtothree);
		return "homeWeb";
	}
	@RequestMapping(value="/home/listMovie",method = RequestMethod.GET)
	public String viewListMoviePage(Model model) {
		model.addAttribute("listCategory", categoryService.getAllCategory());
		
		return "MovieWeb/ListMovie";
	}
	
}
