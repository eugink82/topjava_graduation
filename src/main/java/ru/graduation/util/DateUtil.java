package ru.graduation.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class DateUtil {
    public static LocalDate CURR_DATE=LocalDate.of(2015,11,30);
    public static LocalDateTime DATETIME_VOTE=LocalDateTime.of(DateUtil.CURR_DATE, LocalTime.of(11,15));
}
