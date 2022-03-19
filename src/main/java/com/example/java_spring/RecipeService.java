package com.example.java_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class RecipeService {
    private RecipesRepository recipesRepository;

    @Autowired
    public RecipeService(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public Optional<Recipe> findById(Long id) {
        return recipesRepository.findById(id);
    }

    public Iterable<Recipe> getAllRecipes() {
        return recipesRepository.findAll();
    }

    public Long save(Recipe recipe) {
        recipe.setDate(LocalDateTime.now());
        recipesRepository.save(recipe);
        return recipe.getId();
    }

    public void updateById(Recipe recipe, Long id){
        Recipe correntRecipe = recipesRepository.findById(id).get();
        recipe.setId(correntRecipe.getId());
        save(recipe);
    }

    public Optional<List<Recipe>> findByCategory(String category){
        List<Recipe> recipesWithRequestCategory = new ArrayList<>();
        for(Recipe recipe: recipesRepository.findAll()) {
            if (recipe.getCategory() != null && recipe.getCategory().equals(category)) {
                recipesWithRequestCategory.add(recipe);
            }
        }
        return Optional.of(recipesWithRequestCategory);
    }

    public Optional<List<Recipe>> findByName(String name){
        List<Recipe> recipesWithRequestName = new ArrayList<>();
        for(Recipe recipe: recipesRepository.findAll()) {
            if (recipe.getName().equals(name)) {
                recipesWithRequestName.add(recipe);
            }
        }
        return Optional.of(recipesWithRequestName);
    }

    public void deteleById(Long id) {
        recipesRepository.deleteById(id);
    }
}