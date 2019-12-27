package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;

public class AbstractBaseEntity {

    @Getter
    @Setter
    protected Integer id;

    public AbstractBaseEntity() {
    }

    public AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public boolean isNew(){
        return this.id==null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }
}
