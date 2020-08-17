package com.example.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.MovieDTO;
import com.example.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	@Transactional
	@Modifying
	@Query(value="SELECT movie.* from movie  inner join schedule  on movie.id=schedule.movie_id ORDER BY schedule.time ASC LIMIT 2 ",nativeQuery = true)
	List<Movie> getByMovieByDateEarly();
	
	@Transactional
	@Modifying
	@Query(value="SELECT movie.* from movie  inner join schedule  on movie.id=schedule.movie_id ORDER BY schedule.time DESC LIMIT 4 ",nativeQuery = true)
	List<Movie> getByMovieByDate();
	
	@Transactional
	@Modifying
	@Query(value="SELECT movie.* from movie  inner join schedule  on movie.id=schedule.movie_id ORDER BY schedule.time ",nativeQuery = true)
	List<Movie> getAllMovieDescDate();
	

	
}
