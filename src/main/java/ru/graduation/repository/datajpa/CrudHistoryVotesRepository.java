package ru.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.graduation.model.HistoryVotes;

import java.time.LocalDate;
import java.util.List;

public interface CrudHistoryVotesRepository extends JpaRepository<HistoryVotes,Integer> {

    @Query("select h from HistoryVotes h where h.date=?1")
    List<HistoryVotes> getVotesByDate(LocalDate date);
}
