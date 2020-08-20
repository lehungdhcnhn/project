package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long>,PagingAndSortingRepository<Room,Long> {

}
