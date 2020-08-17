package com.example.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.dto.MovieDTO;
import com.example.entities.Movie;
@Component
public class MovieConverter {
	static ModelMapper modelMapper = new ModelMapper();
	public MovieDTO convertToMovieDTO(Movie movie)
	{
		MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
		//movieDTO.setCategoryCode(movie.getCategory().getCode());
		return movieDTO;
	}
	public Movie convertToMovieEntity(MovieDTO movieDTO)
	{
		Movie movie = modelMapper.map(movieDTO, Movie.class);
		return movie;
	}
	
}
