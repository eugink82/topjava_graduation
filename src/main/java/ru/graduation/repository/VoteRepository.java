package ru.graduation.repository;

import ru.graduation.model.Restaurant;
import ru.graduation.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

   Vote save(Vote vote, int userId);

   Vote getVoteByUserId(LocalDate date, int userId);

//   void getCountVoteByRestaurants();
//
//   List<Vote> getVotesByRestaurantId(int restaurant_id);
}
