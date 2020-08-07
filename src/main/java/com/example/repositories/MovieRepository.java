package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.entities.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {




}
