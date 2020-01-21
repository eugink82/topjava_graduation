package ru.graduation.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Vote;
import ru.graduation.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

//@Repository
@Transactional(readOnly = true)
public class VoteRepositoryImpl implements VoteRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote) {
        if(vote.isNew()){
            em.persist(vote);
            return vote;
        }
        return em.merge(vote);
    }

    @Override
    public Vote getVoteByUserId(LocalDate date, int userId) {
        List<Vote> votes=em.createQuery("select v FROM Vote v where v.voteDate=:date and v.user.id=:userId",Vote.class)
                .setParameter("date", date)
                .setParameter("userId",userId).getResultList();
        return DataAccessUtils.singleResult(votes);
    }
}
