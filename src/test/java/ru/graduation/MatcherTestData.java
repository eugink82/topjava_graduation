package ru.graduation;

import ru.graduation.model.Dish;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
}
