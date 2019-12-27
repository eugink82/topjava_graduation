package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;

public class AbstractNamedEntity extends AbstractBaseEntity {

    @Getter
    @Setter
    protected String name;

    public AbstractNamedEntity() {
    }

    public AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + name + ')';
    }
}
