package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.schedule;

public interface ScheduleRepository extends JpaRepository<schedule, Long>{

}
