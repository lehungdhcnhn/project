package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.schedule;

public interface ScheduleRepository extends JpaRepository<schedule, Long>{
	@Transactional
	@Modifying
	@Query("Delete schedule Where movie_id = ?1 ")
	void deleteFindByMovieID(Long movieId);
}
