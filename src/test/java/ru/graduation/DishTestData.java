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

    public static final Dish OBLOMOV_DISH1=new Dish(DISH_OBLOMOV_ID, CURR_DATE,
            "Маринованная селедка с овощами",new BigDecimal("160.00"),RestaurantTestData.OBLOMOV);

    public static final Dish OBLOMOV_DISH2=new Dish(DISH_OBLOMOV_ID+1, CURR_DATE,
            "Цыплята табака",new BigDecimal("220.00"),RestaurantTestData.OBLOMOV);

    public static final Dish OBLOMOV_DISH3=new Dish(DISH_OBLOMOV_ID+2, CURR_DATE,
            "Суп старополтавский",new BigDecimal("155.00"),RestaurantTestData.OBLOMOV);

    public static final Dish OBLOMOV_DISH4=new Dish(DISH_OBLOMOV_ID+3, CURR_DATE,
            "Вишневый морс",new BigDecimal("80.00"),RestaurantTestData.OBLOMOV);





}
