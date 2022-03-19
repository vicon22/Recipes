package com.example.java_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id) {
        //System.out.println(recipeService.findById(id));
        return ResponseEntity.of(recipeService.findById(id));
    }

    @GetMapping("/all")
    public Iterable<Recipe> getRecipe() {
        System.out.println(recipeService.getAllRecipes());
        return recipeService.getAllRecipes();
    }

    @PostMapping("/new")
    public Map<String, Long> postRecipe(@RequestBody @Valid Recipe recipe) {
        Long id = recipeService.save(recipe);
        return Map.of("id", id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody @Valid Recipe recipe) {
        if (recipeService.findById(id).isPresent()){
            recipeService.updateById(recipe, id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipe(@RequestParam Map<String, String> param){
        //System.out.println(param);
        if (param.containsKey("name") && param.size() == 1) {
            return ResponseEntity.of(recipeService.findByName(param.get("name")));
        }else if (param.containsKey("category") && param.size() == 1){
            //System.out.println("------------");
            return ResponseEntity.of(recipeService.findByCategory(param.get("category")));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable Long id){
        if (recipeService.findById(id).isPresent()){
            recipeService.deteleById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
