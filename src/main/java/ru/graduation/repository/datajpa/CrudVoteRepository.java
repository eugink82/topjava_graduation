package ru.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Vote;

import java.time.LocalDate;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote,Integer> {

    @Query("select v FROM Vote v where v.voteDate=:date and v.user.id=:userId")
    Vote getVoteByUserId(@Param("date") LocalDate date,@Param("userId") int userId);
}
