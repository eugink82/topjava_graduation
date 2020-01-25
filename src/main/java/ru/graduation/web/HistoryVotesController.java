package ru.graduation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.graduation.model.HistoryVotes;
import ru.graduation.repository.datajpa.CrudHistoryVotesRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value= HistoryVotesController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoryVotesController {

    public static final String REST_URL="/profile/history/votes";

    private static final Sort SORT_DATE=new Sort(Sort.Direction.DESC,"date");

    @Autowired
    private CrudHistoryVotesRepository repository;

    @GetMapping
    public List<HistoryVotes> getHistoryVotes(){
        return repository.findAll(SORT_DATE);
    }

    @GetMapping("/{date}")
    public List<HistoryVotes> getHistoryVotesByDate(@PathVariable LocalDate date){
        return repository.getVotesByDate(date);
    }
}
