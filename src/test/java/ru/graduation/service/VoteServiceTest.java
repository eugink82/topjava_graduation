package ru.graduation.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.graduation.model.Vote;
import ru.graduation.util.exception.DeadLineException;

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
    private VoteService service;

   // @Before

    @Test
    public void getVote(){
       Vote actualVote=service.getVoteByUserId(DATE_VOTE,USER_ID);
       assertMatch(actualVote,REVOTE_OBLOMOV_USER);
    }

//    @Test
//    public void reVote(){
//       Vote actualVote=service.save(REVOTE_TIFLISS_DATETIME,USER_ID);
//        assertMatch(actualVote,REVOTE_TIFLISS_DATETIME);
//    }
//
//    @Test(expected = DeadLineException.class)
//    public void reVoteAfterDeadLineTime(){
//        Vote actualVote=service.save(REVOTE_TIFLISS_DEADLINE_TIME,USER_ID);
//    }
//
//    @Test
//    public void newVote(){
//        Vote actualVote=service.save(NEW_VOTE_TIFLISS_NEW_DATETIME,USER_ID);
//        assertMatch(service.getVoteByUserId(NEW_VOTE_TIFLISS_NEW_DATETIME.getVote_date(),USER_ID),actualVote);
//    }
}
