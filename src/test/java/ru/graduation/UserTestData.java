package ru.graduation;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.graduation.model.Restaurant;
import ru.graduation.model.User;
import ru.graduation.model.Role;
import ru.graduation.model.User;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.graduation.MatcherTestData.assertMatch;
import static ru.graduation.TestUtil.readFromJsonMvcResult;
import static ru.graduation.TestUtil.readListFromJsonMvcResult;
import static ru.graduation.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER);

    public static ResultMatcher contentJson(User... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, User.class), Arrays.asList(expected));
    }

    public static ResultMatcher contentJson(User expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, User.class), expected);
    }

}
