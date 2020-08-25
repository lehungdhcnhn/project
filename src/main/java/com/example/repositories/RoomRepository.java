package com.example.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long>,PagingAndSortingRepository<Room,Long> {
	@Transactional
	@Modifying
	@Query(value="Select distinct room.name, room.id from room  inner join schedule  on room.id=schedule.room_id where movie_id=?1 and Date(time)=Date(?2)",nativeQuery = true)
	List<Room> findRoomByMovieID(Long movieId, Date startTime);
}
