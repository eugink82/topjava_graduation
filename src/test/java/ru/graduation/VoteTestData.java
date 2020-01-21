package ru.graduation;

import ru.graduation.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduation.RestaurantTestData.*;
import static ru.graduation.model.AbstractBaseEntity.START_SEQ;


public class VoteTestData {

    public static final int OBLOMOV_VOTE_ID=START_SEQ+11;
    public static final int TIFLISS_VOTE_ID=START_SEQ+12;
    public static final LocalDateTime DATE_TIME_VOTE=LocalDateTime.of(2015,11,30,10,15);
    public static final LocalDate DATE_VOTE=DATE_TIME_VOTE.toLocalDate();
    public static final LocalTime TIME_VOTE=DATE_TIME_VOTE.toLocalTime();

    public static final Vote REVOTE_TIFLISS_USER=new Vote(OBLOMOV_VOTE_ID,TIFLISS);
    public static final Vote REVOTE_OBLOMOV_USER=new Vote(OBLOMOV_VOTE_ID,OBLOMOV);
    public static final Vote NEW_VOTE_TIFLISS=new Vote(TIFLISS);
    public static final Vote NEW_VOTE_OBLOMOV=new Vote(OBLOMOV);

    public static final Vote REVOTE_TIFLISS_DATETIME=new Vote(OBLOMOV_VOTE_ID,TIFLISS, LocalDateTime.of(2015,11,30,10,15));
    public static final Vote REVOTE_TIFLISS_DEADLINE_TIME=new Vote(OBLOMOV_VOTE_ID,TIFLISS, LocalDateTime.of(2015,11,30,11,15));
    public static final Vote REVOTE_OBLOMOV_DATETIME_FAIL_TIME=new Vote(OBLOMOV_VOTE_ID,OBLOMOV, LocalDateTime.of(2015,11,30,11,15));
    public static final Vote NEW_VOTE_TIFLISS_NEW_DATETIME=new Vote(TIFLISS, LocalDateTime.of(2015,12,1,10,15));
    public static final Vote NEW_VOTE_OBLOMOV_NEW_DATETIME=new Vote(OBLOMOV);

}
