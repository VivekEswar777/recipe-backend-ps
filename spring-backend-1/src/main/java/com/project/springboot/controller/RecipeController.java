package com.project.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.springboot.models.Recipe;
import com.project.springboot.services.RecipeService;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin
public class RecipeController {
	
	@Autowired
	RecipeService recipeService;
	
	@PostMapping("/load")
    public ResponseEntity<List<Recipe>> loadRecipes() {
        return ResponseEntity.ok(recipeService.loadRecipesFromExternalAPI());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipes(@RequestParam("q") String query) {
        return ResponseEntity.ok(recipeService.searchByNameOrCuisine(query));
    }
    
    @GetMapping("/searchByStream")
    public ResponseEntity<List<Recipe>> searchByNormalizedQuery(@RequestParam("q") String query) {
        return ResponseEntity.ok(recipeService.searchByNormalizedQuery(query));
    }



}
