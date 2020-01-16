package ru.graduation.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.graduation.model.Vote;
import ru.graduation.util.DateUtil;

import java.time.LocalDate;

import static ru.graduation.RestaurantTestData.*;
import static ru.graduation.UserTestData.*;
import static ru.graduation.MatcherTestData.*;
import static ru.graduation.VoteTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VoteServiceTest {

    @Autowired
    private VoteServiceImpl service;

   // @Before

    @Test
    public void getVote(){
       Vote actualVote=service.getVoteByUserId(USER_ID);
       assertMatch(actualVote,REVOTE_OBLOMOV_USER);
    }

    @Test
    public void pollExistVote(){
       Vote actualVote=service.save(REVOTE_TIFLISS_USER,USER_ID);
        assertMatch(actualVote,REVOTE_TIFLISS_USER);
    }

    @Test
    public void pollVote(){
       // DateUtil.CURR_DATE= LocalDate.of(2015,12,1);
        Vote actualVote=service.save(NEW_VOTE_TIFLISS,USER_ID);
        assertMatch(service.getVoteByUserId(USER_ID),actualVote);
      //  DateUtil.CURR_DATE= LocalDate.of(2015,11,30);
    }
}
