package ru.graduation.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import ru.graduation.model.Vote;

import java.time.LocalDate;

@Getter
@Setter
public class VoteTo {

    @JsonIgnore
    private Integer id;

    private String email;

    private LocalDate date;

    private String name;

    public VoteTo(@Nullable Vote vote) {
        this.id = vote.getId();
        this.email=vote.getUser().getEmail();
        this.date = vote.getVoteDate();
        this.name = vote.getRestaurant().getName();
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "email='" + email + '\'' +
                ", date=" + date +
                ", name='" + name + '\'' +
                '}';
    }
}
