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
    public static final int TIFLISS_VOTE_ID=START_SEQ+12;

    public static final Vote REVOTE_TIFLISS_USER=new Vote(OBLOMOV_VOTE_ID,TIFLISS);
    public static final Vote REVOTE_OBLOMOV_USER=new Vote(OBLOMOV_VOTE_ID,OBLOMOV);
    public static final Vote NEW_VOTE_TIFLISS=new Vote(TIFLISS);
    public static final Vote NEW_VOTE_OBLOMOV=new Vote(OBLOMOV);

}
