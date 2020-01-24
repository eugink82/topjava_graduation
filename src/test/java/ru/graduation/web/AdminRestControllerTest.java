package ru.graduation.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import ru.graduation.TestUtil;
import ru.graduation.model.Role;
import ru.graduation.model.User;
import ru.graduation.service.UserService;
import ru.graduation.web.json.JsonUtil;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.MatcherTestData.*;
import static ru.graduation.TestUtil.userHttpBasic;
import static ru.graduation.UserTestData.contentJson;
import static ru.graduation.UserTestData.*;

public class AdminRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL=AdminRestController.ADMIN_URL+"/";

    @Autowired
    private UserService service;

    @Test
    public void testGet() throws Exception{
        mockMvc.perform(get(REST_URL+ADMIN_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(ADMIN)));
    }

    @Test
    public void testGetByEmail() throws Exception{
        mockMvc.perform(get(REST_URL+"by?email="+ADMIN.getEmail())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(ADMIN)));
    }

    @Test
    public void testCreate() throws Exception{
        User expected=new User(null,"Boris","Borya@mail.ru","qwerty", Role.ROLE_USER,Role.ROLE_ADMIN);
        ResultActions action=mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());
        User returned= TestUtil.readFromJson(action,User.class);
        expected.setId(returned.getId());
        assertMatch(returned,expected);
        assertMatch(service.getAll(),ADMIN,expected,USER);
    }

    @Test
    public void testUpdate() throws Exception {
        User updated=new User(USER);
        updated.setName("Sergey");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        mockMvc.perform(put(REST_URL+USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        assertMatch(service.get(USER_ID),updated);
    }


    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(delete(REST_URL+USER_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
        assertMatchList(service.getAll(),ADMIN);
    }

    @Test
    public void getAll() throws Exception{
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(ADMIN,USER));
    }

    @Test
    public void getUnAuth() throws Exception{
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    public void getForbidden() throws Exception{
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }
}