package ru.graduation.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Restaurant;
import ru.graduation.model.User;
import ru.graduation.model.Vote;
import ru.graduation.util.DateUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class VoteRepositoryImpl implements  VoteRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId) {
        if(!vote.isNew() && getVoteByUserId(userId)==null){
            return null;
        }
        vote.setUser(em.getReference(User.class,userId));
        vote.setRestaurant(em.getReference(Restaurant.class,vote.getRestaurant().getId()));
        if(vote.isNew()){
            em.persist(vote);
            return vote;
        }
        return em.merge(vote);
    }

    @Override
    public Vote getVoteByUserId(int userId) {
        List<Vote> votes=em.createQuery("select v FROM Vote v where v.vote_date=:date and v.user.id=:userId",Vote.class)
                .setParameter("date", DateUtil.CURR_DATE)
                .setParameter("userId",userId).getResultList();
        return DataAccessUtils.singleResult(votes);
    }
}
