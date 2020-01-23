package ru.graduation;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.graduation.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduation.TestUtil.*;

public class MatcherTestData {

    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualTo(expected);
    }

    @SafeVarargs
    public static <T> void assertMatchList(Iterable<T> actual, T... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    @SafeVarargs
    public static <T> void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static <T> void assertMatch(Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).isEqualTo(expected);
    }

//    @SafeVarargs
//    public static <T> ResultMatcher contentJson(T... expected) {
//        return result -> assertMatch(readListFromJsonMvcResult(result, expected[0].getClass()), Arrays.asList(expected));
//    }
//
//    public static <T> ResultMatcher contentJson(T expected) {
//        return result -> assertMatch(readFromJsonMvcResult(result, expected.getClass()), expected);
//    }
}
