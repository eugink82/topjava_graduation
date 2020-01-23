package ru.graduation.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.graduation.model.Role;
import ru.graduation.model.User;
import ru.graduation.service.UserService;
import ru.graduation.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.MatcherTestData.assertMatch;
import static ru.graduation.MatcherTestData.assertMatchList;
import static ru.graduation.UserTestData.*;

public class ProfileRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL=ProfileRestController.PROFILE_URL+"/";

    @Autowired
    private UserService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER));
    }

    @Test
    public void update() throws Exception{
       User created=new User(USER_ID,"Gleban","Gleb@rambler.ru","qwerty45", Role.ROLE_USER);
       mockMvc.perform(put(REST_URL)
               .contentType(MediaType.APPLICATION_JSON)
               .content(JsonUtil.writeValue(created)))
               .andExpect(status().isNoContent());
       assertMatch(service.getByEmail("Gleb@rambler.ru"),created);
    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(delete(REST_URL))
                .andExpect(status().isNoContent());
        assertMatchList(service.getAll(),ADMIN);
    }
}