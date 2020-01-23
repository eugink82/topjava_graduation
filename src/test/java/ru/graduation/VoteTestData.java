package ru.graduation;

import ru.graduation.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduation.RestaurantTestData.*;
import static ru.graduation.model.AbstractBaseEntity.START_SEQ;


public class VoteTestData {

    public static final int TIFLISS_VOTE_ID=START_SEQ+19;
    public static final int OBLOMOV_VOTE_ID=START_SEQ+20;

    public static final Vote VOTE_TIFLIS=new Vote(TIFLISS_VOTE_ID,TIFLISS);
    public static final Vote VOTE_OBLOMOV=new Vote(OBLOMOV);
    public static final Vote RE_VOTE_OBLOMOV=new Vote(TIFLISS_VOTE_ID,OBLOMOV);

}
