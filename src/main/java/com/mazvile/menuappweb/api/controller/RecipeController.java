package com.mazvile.menuappweb.api.controller;

import com.mazvile.menuappweb.api.dto.request.RecipeRequestDto;
import com.mazvile.menuappweb.api.dto.response.RecipeResponseDto;
import com.mazvile.menuappweb.repository.entity.Recipe;
import com.mazvile.menuappweb.service.recipe.RecipeService;
import com.mazvile.menuappweb.api.transformer.RecipeTransformer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeTransformer recipeTransformer;

    public RecipeController(RecipeService recipeService, RecipeTransformer recipeTransformer) {
        this.recipeService = recipeService;
        this.recipeTransformer = recipeTransformer;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RecipeResponseDto createProduct(@RequestBody final RecipeRequestDto recipeRequestDto) {
        final Recipe recipe = recipeService.addRecipe(recipeTransformer.fromRequest(recipeRequestDto));
        return recipeTransformer.toResponse(recipe);
    }
}
