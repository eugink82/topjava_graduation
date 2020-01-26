package ru.graduation.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.graduation.model.AbstractBaseEntity;
import ru.graduation.model.Dish;
import ru.graduation.model.Restaurant;
import ru.graduation.util.exception.NotFoundException;

import java.util.*;

import static ru.graduation.DishTestData.*;
import static ru.graduation.RestaurantTestData.*;
import static ru.graduation.MatcherTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceTest {

    private static final Logger log = LoggerFactory.getLogger(RestaurantServiceTest.class);

    private static final Comparator<AbstractBaseEntity> ENTITY_COMPARATOR=Comparator.comparing(AbstractBaseEntity::getId);

    @Autowired
    private RestaurantService service;

    @Test
    public void create() {
        Restaurant newR = new Restaurant("Санчо Пансо");
        Restaurant createdR = service.create(new Restaurant(newR));
        newR.setId(createdR.getId());
        assertMatch(createdR,newR);
        assertMatch(service.getAll(),OBLOMOV,newR,TIFLISS);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateNameCreate(){
        service.create(new Restaurant("Обломов"));
    }

    @Test
    public void update() {
        Restaurant updated=new Restaurant(OBLOMOV);
        updated.setName("Обломов1");
        service.update(new Restaurant(updated));
        assertMatch(service.get(OBLOMOV_ID),updated);
    }

    @Test
    public void get() {
        Restaurant restaurant=service.get(OBLOMOV_ID);
        assertMatch(restaurant,OBLOMOV);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound(){
        service.get(1);
    }

    @Test
    public void getByName(){
        Restaurant restaurant=service.getByName(OBLOMOV.getName());
        assertMatch(restaurant,OBLOMOV);
    }

    @Test
    public void delete() {
        service.delete(OBLOMOV_ID);
        assertMatchList(service.getAll(),TIFLISS);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(1);
    }

    @Test
    public void getAll() {
       assertMatch(service.getAll(),OBLOMOV,TIFLISS);
    }

    @Test
    public void getWithDishes() {
       Set<Dish> dishes=new TreeSet<>(ENTITY_COMPARATOR);
       Restaurant oblomov=service.getWithDishes(OBLOMOV_ID);
       dishes.addAll(oblomov.getDishes());
       Assertions.assertThat(oblomov).isEqualTo(OBLOMOV);
       assertMatch(dishes,OBLOMOV_DISH1,OBLOMOV_DISH2,OBLOMOV_DISH3,OBLOMOV_DISH4);
//      assertMatch((oblomov.getDishes()).subList(0,3),OBLOMOV_DISH1,OBLOMOV_DISH2,OBLOMOV_DISH3,OBLOMOV_DISH4);
    }

    @Test(expected = NotFoundException.class)
    public void getWithDishesNotFound() throws Exception{
        service.getWithDishes(1);
    }

}