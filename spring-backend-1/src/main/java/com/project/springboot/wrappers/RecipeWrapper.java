package com.project.springboot.wrappers;

import java.util.List;

import com.project.springboot.models.Recipe;

public class RecipeWrapper {
	
	private List<Recipe> recipes;

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	

}
