package ru.graduation.to;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HistoryMenuTo {

    private String name;

    private LocalDate date;

    public HistoryMenuTo() {
    }


}
