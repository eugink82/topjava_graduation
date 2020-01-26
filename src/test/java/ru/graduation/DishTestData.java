package ru.graduation;

import ru.graduation.model.Dish;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static ru.graduation.model.AbstractBaseEntity.*;

public class DishTestData {
    public static final LocalDate ASSIGN_DATE=LocalDate.now().minusDays(1);

    public static final int DISH_TIFLIS_ID= START_SEQ+6;
    public static final int DISH_OBLOMOV_ID= START_SEQ+9;

    public static final Dish OBLOMOV_DISH1=new Dish(DISH_OBLOMOV_ID,
            "Маринованная селедка с овощами",new BigDecimal("160.00"));

    public static final Dish OBLOMOV_DISH2=new Dish(DISH_OBLOMOV_ID+1,
            "Цыплята табака",new BigDecimal("220.00"));

    public static final Dish OBLOMOV_DISH3=new Dish(DISH_OBLOMOV_ID+2,
            "Суп старополтавский",new BigDecimal("155.00"));

    public static final Dish OBLOMOV_DISH4=new Dish(DISH_OBLOMOV_ID+3,
            "Вишневый морс",new BigDecimal("80.00"));


    public static final Dish OBLOMOV_DISH1_ASSIGN_DATE=new Dish(DISH_OBLOMOV_ID+5,ASSIGN_DATE,
            "Ципилинай",new BigDecimal("150.00"));

    public static final Dish OBLOMOV_DISH2_ASSIGN_DATE=new Dish(DISH_OBLOMOV_ID+6,ASSIGN_DATE,
            "Шулюм",new BigDecimal("115.00"));

    public static final Dish OBLOMOV_DISH3_ASSIGN_DATE=new Dish(DISH_OBLOMOV_ID+7,ASSIGN_DATE,
            "Шашлык по грузински",new BigDecimal("160.00"));

    public static final Dish OBLOMOV_DISH4_ASSIGN_DATE=new Dish(DISH_OBLOMOV_ID+8,ASSIGN_DATE,
            "Квас",new BigDecimal("220.00"));

    public static final List<Dish> LIST_DISH_ASSIGN_DATE= Arrays.asList(OBLOMOV_DISH1_ASSIGN_DATE,
            OBLOMOV_DISH2_ASSIGN_DATE,OBLOMOV_DISH3_ASSIGN_DATE,OBLOMOV_DISH4_ASSIGN_DATE);


    public static Dish getCreated() {
        return new Dish(null,"Цепелинай", new BigDecimal("114.00"));
    }

    public static Dish getCreatedWithAssignDate() {
        Dish dish=getCreated();
        dish.setDate(ASSIGN_DATE);
        return dish;
    }

    public static Dish getUpdated() {
        return new Dish(DISH_OBLOMOV_ID,"Свекольник", new BigDecimal("190.00"));
    }




}
