package com.mazvile.menuappweb.repository.entity;

import com.mazvile.menuappweb.model.enums.RecipeType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue
    @Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RecipeType dishType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ingredient")
    private Set<Ingredient> products;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecipeType getDishType() {
        return dishType;
    }

    public void setDishType(RecipeType dishType) {
        this.dishType = dishType;
    }

    public Set<Ingredient> getIngredients() {
        return products;
    }

    public void setProducts(Set<Ingredient> products) {
        this.products = products;
    }


    public static final class RecipeBuilder {
        private UUID id;
        private String name;
        private String description;
        private RecipeType dishType;
        private Set<Ingredient> products;

        private RecipeBuilder() {
        }

        public static RecipeBuilder aRecipe() {
            return new RecipeBuilder();
        }

        public RecipeBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public RecipeBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public RecipeBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public RecipeBuilder withDishType(RecipeType dishType) {
            this.dishType = dishType;
            return this;
        }

        public RecipeBuilder withProducts(Set<Ingredient> products) {
            this.products = products;
            return this;
        }

        public Recipe build() {
            Recipe recipe = new Recipe();
            recipe.setId(id);
            recipe.setName(name);
            recipe.setDescription(description);
            recipe.setDishType(dishType);
            recipe.setProducts(products);
            return recipe;
        }
    }
}
