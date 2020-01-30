package com.mazvile.menuappweb.repository.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue
    @Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product ingredient) {
        this.product = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public static final class IngredientBuilder {
        private UUID uuid;
        private Product product;
        private int quantity;

        private IngredientBuilder() {
        }

        public static IngredientBuilder anIngredient() {
            return new IngredientBuilder();
        }

        public IngredientBuilder withUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public IngredientBuilder withIngredient(Product ingredient) {
            this.product = ingredient;
            return this;
        }

        public IngredientBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Ingredient build() {
            Ingredient ingredient = new Ingredient();
            ingredient.setUuid(uuid);
            ingredient.setProduct(product);
            ingredient.setQuantity(quantity);
            return ingredient;
        }
    }
}
