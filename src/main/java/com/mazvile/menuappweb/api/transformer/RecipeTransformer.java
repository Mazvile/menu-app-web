package com.mazvile.menuappweb.api.transformer;

import com.mazvile.menuappweb.api.dto.IngredientDto;
import com.mazvile.menuappweb.api.dto.request.RecipeRequestDto;
import com.mazvile.menuappweb.api.dto.response.ProductResponseDto;
import com.mazvile.menuappweb.api.dto.response.RecipeResponseDto;
import com.mazvile.menuappweb.api.dto.response.RecipeResponseDtoBuilder;
import com.mazvile.menuappweb.repository.ProductRepository;
import com.mazvile.menuappweb.repository.entity.Ingredient;
import com.mazvile.menuappweb.repository.entity.Product;
import com.mazvile.menuappweb.repository.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class RecipeTransformer {
    private final ProductRepository productRepository;

    public RecipeTransformer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Recipe fromRequest(final RecipeRequestDto recipeRequestDto) {
        return Recipe.RecipeBuilder.aRecipe()
                .withName(recipeRequestDto.getName())
                .withDishType(recipeRequestDto.getDishType())
                .withDescription(recipeRequestDto.getDescription())
                .withProducts(transformProducts(recipeRequestDto.getIngredients()))
                .build();
    }

    private Set<Ingredient> transformProducts(Set<IngredientDto> products) {
        return products.stream()
                .map(ingredient -> {
                    return Ingredient.IngredientBuilder
                            .anIngredient()
                            .withIngredient(transformIntoProduct(ingredient))
                            .withQuantity(ingredient.getQuantity())
                            .build();

                }).collect(Collectors.toSet());
    }

    private Product transformIntoProduct(IngredientDto ingredient) {
        return productRepository.getOne(ingredient.getProductUuid());
    }

    public static RecipeResponseDto toResponse(final Recipe recipe) {
        return RecipeResponseDtoBuilder
                .aRecipeResponseDto()
                .withDescription(recipe.getDescription())
                .withDishType(recipe.getDishType())
                .withName(recipe.getName())
                .withIngredients(transformToResponseIngredients(recipe.getIngredients()))
                .build();
    }

    private static Set<ProductResponseDto> transformToResponseIngredients(Set<Ingredient> ingredients) {
        return ingredients.stream()
        .map(ingredient -> {
            return ProductResponseDto.ProductResponseDtoBuilder
                    .aProductResponseDto()
                    .withUnits(ingredient.getProduct().getUnits())
                    .withName(ingredient.getProduct().getName())
                    .withUuid(ingredient.getUuid())
                    .build();
        }).collect(Collectors.toSet());
    }


}
