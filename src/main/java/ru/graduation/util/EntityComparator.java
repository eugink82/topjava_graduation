package ru.graduation.util;

import ru.graduation.model.AbstractBaseEntity;
import ru.graduation.model.Dish;

import java.util.Comparator;

public class EntityComparator implements Comparator<AbstractBaseEntity> {
    @Override
    public int compare(AbstractBaseEntity o1, AbstractBaseEntity o2) {
        if (o1.getId() > o2.getId()) {
            return 1;
        } else if (o1.getId() < o2.getId()) {
            return -1;
        }
        return 0;
    }
}
