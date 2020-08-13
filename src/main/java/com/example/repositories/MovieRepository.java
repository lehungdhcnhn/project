package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {




}
