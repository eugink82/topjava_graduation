package ru.graduation;


import org.springframework.test.web.servlet.ResultMatcher;
import ru.graduation.model.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduation.MatcherTestData.assertMatch;
import static ru.graduation.TestUtil.readFromJsonMvcResult;
import static ru.graduation.TestUtil.readListFromJsonMvcResult;
import static ru.graduation.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int TIFLISS_ID=START_SEQ+2;
    public static final int OBLOMOV_ID=START_SEQ+3;

    public static final Restaurant TIFLISS=new Restaurant(TIFLISS_ID,"Тифлисский дворик");
    public static final Restaurant OBLOMOV=new Restaurant(OBLOMOV_ID,"Обломов");

    public static ResultMatcher contentJson(Restaurant... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, Restaurant.class), Arrays.asList(expected));
    }

    public static ResultMatcher contentJson(Restaurant expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, Restaurant.class), expected);
    }

}
