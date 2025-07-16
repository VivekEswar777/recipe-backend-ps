package com.project.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.springboot.models.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer>{
	
	List<Recipe> findByNameContainingIgnoreCaseOrCuisineContainingIgnoreCase(String name, String cuisine);

}
