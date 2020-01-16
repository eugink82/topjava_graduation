package ru.graduation.repository;

import ru.graduation.model.Restaurant;
import ru.graduation.model.Vote;

import java.util.List;

public interface VoteRepository {

   Vote save(Vote vote, int userId);

   Vote getVoteByUserId(int userId);

//   void getCountVoteByRestaurants();
//
//   List<Vote> getVotesByRestaurantId(int restaurant_id);
}
