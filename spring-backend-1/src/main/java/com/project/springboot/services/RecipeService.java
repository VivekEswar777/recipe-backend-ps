package com.project.springboot.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.project.springboot.config.AppConstants;
import com.project.springboot.exceptions.ConnectionTimeoutException;
import com.project.springboot.exceptions.InvalidInputException;
import com.project.springboot.exceptions.ResourceNotFoundException;
import com.project.springboot.models.Recipe;
import com.project.springboot.repository.RecipeRepository;
import com.project.springboot.wrappers.RecipeWrapper;

@Service
public class RecipeService {

	@Autowired
	RecipeRepository recipeRepository ;
	
	private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);
	private final RestTemplate restTemplate = new RestTemplate();


	public List<Recipe> loadRecipesFromExternalAPI() {
	    for (int attempt = 1; attempt <= AppConstants.MAX_RETRIES; attempt++) {
        try {
            RecipeWrapper wrapper = restTemplate.getForObject(AppConstants.EXTERNAL_API_URL, RecipeWrapper.class);
            if (wrapper != null && wrapper.getRecipes() != null) {
                logger.info("Loaded {} recipes from external API", wrapper.getRecipes().size());
                return recipeRepository.saveAll(wrapper.getRecipes());
            } else {
            	logger.warn("Received empty or invalid response from API on attempt {}", attempt);
            }
        } catch (ResourceAccessException e) {
        	logger.warn("Unable to connect to the external API Timeout on attempt {}: {}", attempt, e.getMessage());
        } catch (Exception e) {
        	logger.error("Unexpected error on attempt {}: {}", attempt, e.getMessage());
        }
	    
	    try {
            logger.info("Waiting {} seconds before retrying...", AppConstants.RETRY_DELAY_SECONDS);
            Thread.sleep(AppConstants.RETRY_DELAY_SECONDS * 1000L);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            logger.error("Retry sleep interrupted");
            break;
        }
    }

    throw new ConnectionTimeoutException("Failed to fetch recipes from external API after "
            + AppConstants.MAX_RETRIES + " attempts.");
}

	public List<Recipe> getAllRecipes() {
		logger.info("Fetching all recipes from InMemory DB");
		return recipeRepository.findAll();
	}

	public Recipe getRecipeById(Integer id) {
		logger.info("Fetching recipe with ID: {}", id);
		return recipeRepository.findById(id).orElseThrow(() -> {
	        logger.warn("Recipe not found with ID: {}", id);
	        return new ResourceNotFoundException("Recipe not found with ID: " + id);
	    });
		
	}
	
	public List<Recipe> searchByNameOrCuisine(String query) {
	    if (query == null || query.trim().isEmpty()) {
	        throw new InvalidInputException("Search query must not be empty");
	    }
	    logger.info("Performing search for query: {}", query);
	    return recipeRepository.findByNameContainingIgnoreCaseOrCuisineContainingIgnoreCase(query, query);
	}
	
	public List<Recipe> searchByNormalizedQuery(String query) {
	    if (query == null || query.trim().isEmpty()) {
	        throw new InvalidInputException("Search query must not be empty");
	    }

	    String normalizedQuery = normalize(query);
	    logger.info("Normalized search query: {}", normalizedQuery);

	    return recipeRepository.findAll().stream()
	            .filter(recipe ->
	                    normalize(recipe.getName()).contains(normalizedQuery) ||
	                    normalize(recipe.getCuisine()).contains(normalizedQuery))
	            .toList();
	}

	private String normalize(String input) {
	    return input == null ? "" :
	           input.replaceAll("\\s+", "").toLowerCase().trim();
	}



}
