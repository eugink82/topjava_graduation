package ru.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.SecurityUtil;
import ru.graduation.model.Vote;
import ru.graduation.service.VoteService;
import ru.graduation.to.VoteTo;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping(VoteController.VOTE_URL)
public class VoteController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public static final String VOTE_URL="/profile/votes";

    @PutMapping//("/{id}")
    public ResponseEntity<Vote> vote(@RequestParam int restaurantId){
        Vote created=service.save(restaurantId,SecurityUtil.authUserId());
        URI uriOfNewResource= ServletUriComponentsBuilder.fromCurrentContextPath()
                .buildAndExpand(VOTE_URL+"/{id}").toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public VoteTo getMyVote(){
        Vote myVote=service.getVoteByUserId(LocalDate.now(),SecurityUtil.authUserId());
        return myVote!=null ? new VoteTo(myVote) : null;
    }
}
