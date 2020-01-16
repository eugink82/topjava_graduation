package ru.graduation.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.graduation.DishTestData;
import ru.graduation.model.Dish;
import ru.graduation.util.DateUtil;
import ru.graduation.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.*;
import static ru.graduation.DishTestData.*;
import static ru.graduation.RestaurantTestData.*;
import static ru.graduation.MatcherTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceTest {

    @Autowired
    private DishService service;

    @Test
    public void create() {
        Dish created= getCreated();
        Dish actualDish=service.create(created,OBLOMOV_ID);
        created.setId(actualDish.getId());
        assertMatch(actualDish,created);
        assertMatch(service.getMenu(DateUtil.CURR_DATE,OBLOMOV_ID),
                OBLOMOV_DISH1,OBLOMOV_DISH2,OBLOMOV_DISH3,OBLOMOV_DISH4,created);
    }

    @Test
    public void update() {
        Dish updated=getUpdated();
        service.update(updated,OBLOMOV_ID);
        assertMatch(service.get(updated.getId(),OBLOMOV_ID),updated);
    }

    @Test
    public void delete() {
        service.delete(DISH_OBLOMOV_ID,OBLOMOV_ID);
        assertMatch(service.getMenu(DateUtil.CURR_DATE,OBLOMOV_ID),OBLOMOV_DISH2,OBLOMOV_DISH3,OBLOMOV_DISH4);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound(){
        service.delete(1,OBLOMOV_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotOwnRestaurant() throws Exception {
        service.delete(DISH_OBLOMOV_ID, TIFLISS_ID);
    }

    @Test
    public void get() {
        Dish actualDish=service.get(DISH_OBLOMOV_ID,OBLOMOV_ID);
        assertMatch(actualDish,OBLOMOV_DISH1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound(){
        service.get(1,OBLOMOV_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getNotOwnRestaurant(){
        service.get(DISH_OBLOMOV_ID, TIFLISS_ID);
    }

    @Ignore
    @Test
    public void getDishesByName() {
    }

    @Test
    public void getMenu() {
        List<Dish> actualMenu=service.getMenu(DateUtil.CURR_DATE,OBLOMOV_ID);
        assertMatch(actualMenu,OBLOMOV_DISH1,OBLOMOV_DISH2,OBLOMOV_DISH3,OBLOMOV_DISH4);
    }
}