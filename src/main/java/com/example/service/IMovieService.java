package com.example.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.MovieDTO;
import com.example.entities.Movie;


public  interface IMovieService  {
	// List<MovieDTO> getAll(Pageable pageable);
		List<MovieDTO> getAll();
		Movie save(Movie movie);
	    Movie findById(Long movieId);
	    Movie create(Movie movieDetails);
	    
	    void deleleFile(Long id);
	    void saveFile(Movie movie,String fileName,MultipartFile multipartFile) throws IOException;
	    void delete(Long movieId);
	    long getNumOfMovie();
		Slice<Movie> findAll(int page, int size);
		boolean hasRoomById(long Id);
}
