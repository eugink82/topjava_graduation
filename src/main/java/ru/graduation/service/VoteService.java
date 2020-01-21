package ru.graduation.service;

import org.springframework.stereotype.Service;
import ru.graduation.model.Vote;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.UserRepository;
import ru.graduation.repository.VoteRepository;
import ru.graduation.util.exception.DeadLineException;

import java.time.LocalDate;
import java.time.LocalTime;


@Service
public class VoteService {

    public static final LocalTime MAGIC_TIME=LocalTime.of(11,0);

    private VoteRepository voteRepository;

    private UserRepository userRepository;

    private RestaurantRepository restaurantRepository;

    public VoteService(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Vote save(int restaurantId, int userId) {
        Vote vote = new Vote();
        Vote existVote = getVoteByUserId(LocalDate.now(), userId);
        if (existVote == null) {
            vote.setUser(userRepository.get(userId));
            vote.setRestaurant(restaurantRepository.get(restaurantId));
            return voteRepository.save(vote);
        } else if (existVote.getRestaurant().getId() != restaurantId) {
            vote.setId(existVote.getId());
            if (LocalTime.now().isBefore(MAGIC_TIME)) {
                vote.setUser(userRepository.get(userId));
                vote.setRestaurant(restaurantRepository.get(restaurantId));
                return voteRepository.save(vote);
            } else {
                throw new DeadLineException("Вы не можете переголосовать - время голосования вышло!");
            }
        }
        return existVote;
    }

    public Vote getVoteByUserId(LocalDate date, int userId) {
        return voteRepository.getVoteByUserId(date, userId);
    }
}
