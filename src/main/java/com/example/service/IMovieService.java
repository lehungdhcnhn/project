package com.example.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.MovieDTO;
import com.example.entities.Movie;


public  interface IMovieService  {
	// List<MovieDTO> getAll(Pageable pageable);
	List<MovieDTO> getAll();

	    Movie findById(Long movieId);
	    Movie create(Movie movieDetails);
	    void update(Long movieId, Movie movieDetails );
	    void deleleFile(Long id);
	    void saveFile(Movie movie,String fileName,MultipartFile multipartFile) throws IOException;
	    void delete(Long movieId);
	    int getToTalItem();
	    
}
