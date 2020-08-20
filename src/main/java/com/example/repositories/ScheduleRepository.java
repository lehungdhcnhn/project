package com.example.repositories;

import java.util.List;

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

	@Transactional
	@Modifying
	@Query("Select s from schedule s where Month(time)=Month(CURDATE()) AND Day(time)>Day(CURDATE()) AND movie_id=?1 ")
	List<schedule> findScheduleByMonth(Long movieId);
}
