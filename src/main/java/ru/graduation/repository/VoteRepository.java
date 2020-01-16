package ru.graduation.repository;

import ru.graduation.model.Vote;

import java.util.List;

public interface VoteRepository {

   Vote save(Vote vote);

   Vote getVoteByUserId(int user_id);

   void getCountVoteByRestaurants();

   List<Vote> getVotesByRestaurantId(int restaurant_id);
}
