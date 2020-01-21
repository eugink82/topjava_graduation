package ru.graduation.service;

import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.graduation.RestaurantTestData;
import ru.graduation.model.Vote;
import ru.graduation.util.exception.DeadLineException;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private VoteService service;

    // @Before

    @Test
    public void getVote() {
        Vote actualVote = service.getVoteByUserId(LocalDate.now(), ADMIN_ID);
        assertMatch(actualVote, VOTE_TIFLIS);
    }

    @Test
    public void reVote() {
        VoteService.MAGIC_TIME = LocalTime.now().plusMinutes(10);
        Vote actualVote = service.save(OBLOMOV_ID, ADMIN_ID);
        assertMatch(actualVote, RE_VOTE_OBLOMOV);
        VoteService.MAGIC_TIME = LocalTime.of(11, 0);
    }

    @Test(expected = DeadLineException.class)
    public void reVoteAfterDeadLineTime() {
        VoteService.MAGIC_TIME = LocalTime.now().minusMinutes(10);
        Vote actualVote = service.save(OBLOMOV_ID, ADMIN_ID);
        VoteService.MAGIC_TIME = LocalTime.of(11, 0);
    }

    @Test
    public void newVote() {
        Vote actualVote = service.save(OBLOMOV_ID, USER_ID);
        assertMatch(service.getVoteByUserId(LocalDate.now(), USER_ID), actualVote);
    }
}
