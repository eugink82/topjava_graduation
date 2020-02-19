package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "history_votes")
public class HistoryVotes extends AbstractNamedEntity{

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private int votes;
}
