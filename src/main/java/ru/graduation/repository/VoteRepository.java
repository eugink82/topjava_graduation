package ru.graduation.repository;

import ru.graduation.model.Vote;

import java.time.LocalDate;

public interface VoteRepository {

   Vote save(Vote vote);

   Vote getVoteByUserId(LocalDate date, int userId);

}
