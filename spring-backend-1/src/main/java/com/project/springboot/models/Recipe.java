package com.project.springboot.models;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Recipe {

    @Id
    private Integer id;

    private String name;
    
    @ElementCollection
    private List<String> ingredients;
    
    @ElementCollection
    private List<String> instructions;
    
    private Integer prepTimeMinutes;
    private Integer cookTimeMinutes;
    private Integer servings;
    private String difficulty;
    private String cuisine;
    private Integer caloriesPerServing;
    
    @ElementCollection
    private List<String> tags;
    
    private Integer userId;
    private String image;
    private Double rating;
    private Integer reviewCount;

    @ElementCollection
    private List<String> mealType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}

	public Integer getPrepTimeMinutes() {
		return prepTimeMinutes;
	}

	public void setPrepTimeMinutes(Integer prepTimeMinutes) {
		this.prepTimeMinutes = prepTimeMinutes;
	}

	public Integer getCookTimeMinutes() {
		return cookTimeMinutes;
	}

	public void setCookTimeMinutes(Integer cookTimeMinutes) {
		this.cookTimeMinutes = cookTimeMinutes;
	}

	public Integer getServings() {
		return servings;
	}

	public void setServings(Integer servings) {
		this.servings = servings;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public Integer getCaloriesPerServing() {
		return caloriesPerServing;
	}

	public void setCaloriesPerServing(Integer caloriesPerServing) {
		this.caloriesPerServing = caloriesPerServing;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(Integer reviewCount) {
		this.reviewCount = reviewCount;
	}

	public List<String> getMealType() {
		return mealType;
	}

	public void setMealType(List<String> mealType) {
		this.mealType = mealType;
	}


    
    
}
