package ru.graduation.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.graduation.TestUtil;
import ru.graduation.model.Restaurant;
import ru.graduation.model.Role;
import ru.graduation.service.RestaurantService;
import ru.graduation.web.json.JsonUtil;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.MatcherTestData.assertMatch;
import static ru.graduation.MatcherTestData.assertMatchList;
import static ru.graduation.RestaurantTestData.*;

public class RestaurantAdminControllerTest extends AbstractControllerTest{

    private static final String REST_URL=RestaurantAdminController.REST_URL+"/";

    @Autowired
    private RestaurantService service;

    @Test
    public void testCreate() throws Exception {
        Restaurant expected=new Restaurant("Хуторок");
        ResultActions action=mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Restaurant returned= TestUtil.readFromJson(action,Restaurant.class);
        expected.setId(returned.getId());
        assertMatch(returned,expected);
        assertMatch(service.getAll(),OBLOMOV,TIFLISS,expected);


    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL+OBLOMOV_ID))
                .andExpect(status().isNoContent());
        assertMatchList(service.getAll(),TIFLISS);


    }

    @Test
    public void testUpdate() throws Exception{
        Restaurant expected=new Restaurant(OBLOMOV);
        expected.setName("Венский вальс");
        mockMvc.perform(put(REST_URL+OBLOMOV_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isNoContent());
        assertMatch(service.get(OBLOMOV_ID),expected);
    }
}