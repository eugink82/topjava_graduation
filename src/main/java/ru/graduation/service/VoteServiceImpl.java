package ru.graduation.service;

import org.springframework.stereotype.Service;
import ru.graduation.model.Restaurant;
import ru.graduation.model.Vote;
import ru.graduation.repository.VoteRepository;
import ru.graduation.util.DateUtil;
import ru.graduation.util.ValidationUtil;
import ru.graduation.util.exception.DeadLineException;

import java.time.LocalTime;

import static ru.graduation.util.ValidationUtil.*;

@Service
public class VoteServiceImpl {

    private VoteRepository repository;

    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote save(Vote vote, int userId){
        Vote existVote=getVoteByUserId(userId);
        if(existVote==null){
            return repository.save(vote,userId);
        }
        else {
            if (DateUtil.DATETIME_VOTE.toLocalTime().isBefore(LocalTime.of(11, 0)))
                return repository.save(vote, userId);
            else {
                throw new DeadLineException("Вы не можете переголосовать - время голосования вышло!");
            }
        }
    }

    public Vote getVoteByUserId(int userId){
        return repository.getVoteByUserId(userId);
    }
}
