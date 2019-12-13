package ru.graduation.model;

import java.math.BigDecimal;

public class Dish extends AbstractNamedEntity {

    private BigDecimal price;

    public Dish(Integer id, String name, BigDecimal price) {
        super(id, name);
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
