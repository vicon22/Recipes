package com.example.java_spring;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipesRepository extends CrudRepository<Recipe, Long> {
}
