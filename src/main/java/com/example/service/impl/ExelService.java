package com.example.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entities.Movie;
import com.example.helper.ExelHelper;
import com.example.repositories.MovieRepository;

@Service
public class ExelService {
	@Autowired
	private MovieRepository movieRepository;
	public void save(MultipartFile file) {
	    try {
	      List<Movie> tutorials = ExelHelper.excelToTutorials(file.getInputStream());
	      movieRepository.saveAll(tutorials);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
	  }

	  public ByteArrayInputStream load() {
	    List<Movie> tutorials = movieRepository.findAll();

	    ByteArrayInputStream in = ExelHelper.tutorialsToExcel(tutorials);
	    return in;
	  }

	  public List<Movie> getAllTutorials() {
	    return movieRepository.findAll();
	  }

}
