package com.example.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import com.example.entities.Movie;
public interface MovieRepository extends JpaRepository<Movie, Long> ,PagingAndSortingRepository<Movie,Long>{
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
	@Query(value="SELECT movie.* from movie",nativeQuery = true)
	List<Movie> getAllMovieDescDate();
	
	@Transactional
	@Modifying
	@Query(value="SELECT movie.* from movie where DATE(starttime) >CURDATE() ",nativeQuery = true)
	List<Movie> findMovieByStart();
	
	@Transactional
	@Modifying
	@Query(value="SELECT movie.* from movie where DATE(starttime) <=CURDATE() ",nativeQuery = true)
	List<Movie> findMovieByEnd();
	
	@Transactional
	@Modifying
	@Query(value="SELECT movie.* FROM movie WHERE movie.name  LIKE CONCAT('%',?1,'%')",nativeQuery = true)
	List<Movie> findMovieByName(String name);
	
}
