package com.example.service;

import java.util.List;
import java.util.Set;

import com.example.dto.MovieDTO;
import com.example.entities.Movie;


public  interface IMovieService  {
	 List<MovieDTO> getAll();
	    Movie findById(Long movieId);
	    Long create(Movie movieDetails);
	    void update(Long movieId, Movie movieDetails );

	    void delete(Long movieId);

}
