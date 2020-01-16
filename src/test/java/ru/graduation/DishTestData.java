package ru.graduation;

import ru.graduation.model.Dish;
import ru.graduation.util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduation.model.AbstractBaseEntity.*;
import static ru.graduation.util.DateUtil.CURR_DATE;

public class DishTestData {

    public static final int DISH_TIFLIS_ID= START_SEQ+4;
    public static final int DISH_OBLOMOV_ID= START_SEQ+7;

    public static final Dish OBLOMOV_DISH1=new Dish(DISH_OBLOMOV_ID,
            "Маринованная селедка с овощами",new BigDecimal("160.00"));

    public static final Dish OBLOMOV_DISH2=new Dish(DISH_OBLOMOV_ID+1,
            "Цыплята табака",new BigDecimal("220.00"));

    public static final Dish OBLOMOV_DISH3=new Dish(DISH_OBLOMOV_ID+2,
            "Суп старополтавский",new BigDecimal("155.00"));

    public static final Dish OBLOMOV_DISH4=new Dish(DISH_OBLOMOV_ID+3,
            "Вишневый морс",new BigDecimal("80.00"));


    public static Dish getCreated() {
        return new Dish(null,"Цепелинай", new BigDecimal("114.00"));
    }

    public static Dish getUpdated() {
        return new Dish(100009,"Свекольник", new BigDecimal("190.00"));
    }




}
