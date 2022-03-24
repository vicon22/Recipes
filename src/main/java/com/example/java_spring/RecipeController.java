package com.example.java_spring;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<Recipe> getRecipe(@PathVariable Long id, Authentication auth) {
        //System.out.println(recipeService.findById(id));
//        System.out.println("---------");
//        System.out.println(auth.name());
//        System.out.println("---------");
        if (recipeService.findById(id).isPresent()) {
            if (recipeService.findById(id).get().getChefsEmail().equals(auth.getName())) {
                return ResponseEntity.of(recipeService.findById(id));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public Iterable<Recipe> getRecipe() {
        System.out.println(recipeService.getAllRecipes());
        return recipeService.getAllRecipes();
    }

    @PostMapping("/new")
    public Map<String, Long> postRecipe(@RequestBody @Valid Recipe recipe, Authentication auth) {
        recipe.setChefsEmail(auth.getName());
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
        if (param.containsKey("name") && param.size() == 1) {
            return ResponseEntity.of(recipeService.findByName(param.get("name")));
        }else if (param.containsKey("category") && param.size() == 1){
            //System.out.println("------------");
            return ResponseEntity.of(recipeService.findByCategory(param.get("category")));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable Long id, Authentication auth){
        if (recipeService.findById(id).isPresent()){
            if (recipeService.findById(id).get().getChefsEmail().equals(auth.getName())) {
                recipeService.deteleById(id);
                return ResponseEntity.noContent().build();
            } else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
