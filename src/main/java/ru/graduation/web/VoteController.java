package ru.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.SecurityUtil;
import ru.graduation.model.Vote;
import ru.graduation.service.VoteService;

import java.net.URI;

@RestController
@RequestMapping(VoteController.VOTE_URL)
public class VoteController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public static final String VOTE_URL="/restaurants/{id}/votes";

    @PutMapping
    public ResponseEntity<Vote> vote(@PathVariable int id){
        Vote created=service.save(id,SecurityUtil.authUserId());
        URI uriOfNewResource= ServletUriComponentsBuilder.fromCurrentContextPath()
                .buildAndExpand(VOTE_URL+"/{newId}",id,created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
