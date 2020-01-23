package ru.graduation.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.graduation.TestUtil;
import ru.graduation.model.Dish;
import ru.graduation.service.DishService;
import ru.graduation.web.json.JsonUtil;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.DishTestData.*;
import static ru.graduation.MatcherTestData.assertMatch;
import static ru.graduation.RestaurantTestData.OBLOMOV_ID;

public class DishAdminControllerTest extends AbstractControllerTest {

    private static final String REST_URL=DishAdminController.REST_URL+"/";

    @Autowired
    private DishService service;

    @Test
    public void testCreate() throws Exception {
        Dish created=new Dish(null,"Утка по пекински",new BigDecimal(335.00));
        ResultActions action=mockMvc.perform(post(REST_URL,OBLOMOV_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated());
        Dish returned= TestUtil.readFromJson(action,Dish.class);
        created.setId(returned.getId());
        assertMatch(returned,created);
        assertMatch(service.getMenu(LocalDate.now(),OBLOMOV_ID),OBLOMOV_DISH1,
                OBLOMOV_DISH2,OBLOMOV_DISH3,OBLOMOV_DISH4,created);

    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(delete(REST_URL+DISH_OBLOMOV_ID,OBLOMOV_ID))
                .andExpect(status().isNoContent());
        assertMatch(service.getMenu(LocalDate.now(),OBLOMOV_ID),
                OBLOMOV_DISH2,OBLOMOV_DISH3,OBLOMOV_DISH4);
    }

    @Test
    public void getUpdate() throws Exception {
        Dish updated=getUpdated();
        mockMvc.perform(put(REST_URL+DISH_OBLOMOV_ID,OBLOMOV_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        assertMatch(service.get(DISH_OBLOMOV_ID,OBLOMOV_ID),updated);
    }

    @Test
    public void getMenuByDate() throws Exception {
        mockMvc.perform(get(REST_URL+"?dateMenu=",OBLOMOV_ID,LocalDate.now().plusDays(1)))
                .andExpect(status().isOk());
        assertMatch(service.getMenu(LocalDate.now().plusDays(1),OBLOMOV_ID),LIST_DISH_ASSIGN_DATE);
    }
}