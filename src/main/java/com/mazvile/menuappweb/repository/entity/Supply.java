package com.mazvile.menuappweb.repository.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "supply")
public class Supply {

    @Id
    @GeneratedValue
    @Type(type = "pg-uuid")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @OneToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product supply;

    @Column(name="quantity", nullable=false)
    private int quantity;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Product getSupply() {
        return supply;
    }

    public void setSupply(Product supply) {
        this.supply = supply;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public static final class SupplyBuilder {
        private UUID id;
        private Product supply;
        private int quantity;

        private SupplyBuilder() {
        }

        public static SupplyBuilder aSupply() {
            return new SupplyBuilder();
        }

        public SupplyBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public SupplyBuilder withSupply(Product supply) {
            this.supply = supply;
            return this;
        }

        public SupplyBuilder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Supply build() {
            Supply supply = new Supply();
            supply.setId(id);
            supply.setSupply(this.supply);
            supply.setQuantity(quantity);
            return supply;
        }
    }
}
