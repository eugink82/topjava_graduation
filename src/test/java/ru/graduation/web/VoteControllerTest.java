package ru.graduation.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.graduation.TestUtil;
import ru.graduation.model.Vote;
import ru.graduation.service.VoteService;
import ru.graduation.web.json.JsonUtil;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.MatcherTestData.assertMatch;
import static ru.graduation.RestaurantTestData.OBLOMOV_ID;
import static ru.graduation.TestUtil.userHttpBasic;
import static ru.graduation.UserTestData.*;
import static ru.graduation.VoteTestData.VOTE_OBLOMOV;

public class VoteControllerTest extends AbstractControllerTest {

    private static final String VOTE_URL=VoteController.VOTE_URL+"/";

    @Autowired
    private VoteService service;

    @Test
    public void vote() throws Exception{
        Vote newVote=VOTE_OBLOMOV;
        newVote.setUser(USER);
        ResultActions action=mockMvc.perform(put(VOTE_URL+"?restaurantId="+OBLOMOV_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(newVote)))
                .andExpect(status().isCreated());
        Vote returned= TestUtil.readFromJson(action,Vote.class);
        newVote.setId(returned.getId());
        assertMatch(returned,newVote);
        assertMatch(service.getVoteByUserId(LocalDate.now(), USER_ID),newVote);
    }

    @Test
    public void getUnAuth() throws Exception {
        mockMvc.perform(get(VOTE_URL))
                .andExpect(status().isUnauthorized());
    }
}