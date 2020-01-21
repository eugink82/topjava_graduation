package ru.graduation.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.graduation.model.Vote;
import ru.graduation.repository.VoteRepository;

import java.time.LocalDate;

@Repository
public class VoteRepositoryDataJpa implements VoteRepository {

    @Autowired
    private CrudVoteRepository repository;

    @Override
    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    @Override
    public Vote getVoteByUserId(LocalDate date, int userId) {
        return repository.getVoteByUserId(date,userId);
    }
}
