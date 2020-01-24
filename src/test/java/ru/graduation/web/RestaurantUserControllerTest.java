package ru.graduation.web;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.graduation.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.RestaurantTestData.*;
import static ru.graduation.TestUtil.userHttpBasic;
import static ru.graduation.UserTestData.USER;
//import static ru.graduation.MatcherTestData.contentJson;

public class RestaurantUserControllerTest extends AbstractControllerTest{

    private static final String REST_URL=RestaurantUserController.REST_URL+"/";

    @Test
    public void getAll() throws Exception{
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(OBLOMOV,TIFLISS));
    }

    @Test
    public void getAllWithMenu() throws Exception{
        mockMvc.perform(get(REST_URL+"?withMenu=true")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(OBLOMOV,TIFLISS));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL+OBLOMOV_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(OBLOMOV));
    }

    @Test
    public void getByName() throws Exception {
        mockMvc.perform(get(REST_URL+"by?name="+TIFLISS.getName())
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(TIFLISS));
    }

    @Test
    public void getByNameWithMenu() throws Exception {
        mockMvc.perform(get(REST_URL+"by?name="+TIFLISS.getName()+"&withMenu=true")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(TIFLISS));
    }

    public void getUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }
}