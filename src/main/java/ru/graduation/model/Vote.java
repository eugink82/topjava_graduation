package ru.graduation.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.graduation.util.DateUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
//@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(name = "vote_idx", columnNames = {"user_id","date"})})
public class Vote extends AbstractBaseEntity{

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Getter
    @Setter
    @NotNull
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    @Column(name="date")
    private LocalDate vote_date;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable=false)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(Restaurant restaurant) {
        this(null,restaurant);
    }

    public Vote(Integer id,Restaurant restaurant){
        super(id);
        this.vote_date= DateUtil.CURR_DATE;
        this.restaurant=restaurant;
    }


    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", user=" + user +
                ", vote_date=" + vote_date +
                ", restaurant=" + restaurant +
                '}';
    }
}
