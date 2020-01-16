package ru.graduation;

import ru.graduation.model.Vote;
import ru.graduation.util.DateUtil;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduation.UserTestData.*;
import static ru.graduation.RestaurantTestData.*;
import static ru.graduation.model.AbstractBaseEntity.START_SEQ;
import static ru.graduation.model.AbstractBaseEntity.START_SEQ;


public class VoteTestData {

    public static final int OBLOMOV_VOTE_ID=START_SEQ+11;

    public static final Vote VOTE_OBLOMOV_USER=new Vote(OBLOMOV_VOTE_ID,USER, DateUtil.CURR_DATE,OBLOMOV);
    public static final Vote VOTE_OBLOMOV_ADMIN=new Vote(OBLOMOV_VOTE_ID+1,ADMIN, DateUtil.CURR_DATE,OBLOMOV);

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
