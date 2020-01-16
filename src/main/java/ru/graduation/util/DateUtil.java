package ru.graduation.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtil {
    public static final LocalDate CURR_DATE=LocalDate.of(2015,11,30);
    public static final LocalDateTime DATETIME_VOTE=LocalDateTime.of(DateUtil.CURR_DATE, LocalTime.of(9,15));
}
