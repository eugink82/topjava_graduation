package ru.graduation.model;

import lombok.Getter;

import java.util.Set;

public class Restaurant extends AbstractNamedEntity {

    @Getter
    private Set<Menu> menus;

    @Getter
    private Set<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
