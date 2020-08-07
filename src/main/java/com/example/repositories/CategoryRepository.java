package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
