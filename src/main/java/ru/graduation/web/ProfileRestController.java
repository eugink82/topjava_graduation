package ru.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduation.SecurityUtil;
import ru.graduation.model.User;
import ru.graduation.service.UserService;

import static ru.graduation.util.ValidationUtil.*;

@RestController
@RequestMapping(ProfileRestController.PROFILE_URL)
public class ProfileRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public static final String PROFILE_URL="/profile";

    @Autowired
    private UserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(){
        return service.get(SecurityUtil.authUserId());
    }

    @PutMapping(consumes =MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user){
        int id=SecurityUtil.authUserId();
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(){
        int id=SecurityUtil.authUserId();
        log.info("delete User with id={}", id);
        service.delete(id);
    }


}
