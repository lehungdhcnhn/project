package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>,PagingAndSortingRepository<Category, Long>{
	
}
