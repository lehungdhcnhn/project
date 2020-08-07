package com.example.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.converter.MovieConverter;
import com.example.dto.MovieDTO;
import com.example.entities.Category;
import com.example.entities.Movie;
import com.example.repositories.CategoryRepository;
import com.example.repositories.MovieRepository;
import com.example.service.IMovieService;

import ch.qos.logback.core.joran.action.NewRuleAction;
@Service

public class MovieService implements IMovieService {
	@Autowired
	private MovieRepository movieRepository;
	@Autowired 
	private MovieConverter movieConverter;
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public List<MovieDTO> getAll() {
		List<MovieDTO> models = new ArrayList<>();
		List<Movie> entities = (List<Movie>) movieRepository.findAll();
		for (Movie item: entities) {
			MovieDTO newDTO = movieConverter.convertToMovieDTO(item);
			models.add(newDTO);
		}
		return models;
	}
	@Override
	public Movie findById(Long movieId) {
		Optional<Movie> entity =movieRepository.findById(movieId);
		 if (!entity.isPresent()) {
	            throw new RuntimeException("Book Not Found!");
	        }
		return entity.get();
	}
	@Override
	public Long create(Movie movieDetails) {
		movieRepository.save(movieDetails);
		return movieDetails.getId();
	}
	
	@Override
	public void delete(Long movieId) {
		movieRepository.deleteById(movieId);
		
	}
	@Override
	public void update(Long movieId, Movie movieDetails) {
		Optional<Movie> currentMovie=movieRepository.findById(movieId);
		currentMovie.get().setTitle(movieDetails.getTitle());
		currentMovie.get().setThumbnail(movieDetails.getThumbnail());
		currentMovie.get().setsDescription(movieDetails.getsDescription());
		currentMovie.get().setContent(movieDetails.getContent());
		currentMovie.get().setCategories(movieDetails.getCategories());
		movieRepository.save(currentMovie.get());
		
	}
	

	

	
}
