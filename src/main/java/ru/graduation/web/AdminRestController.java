package ru.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.User;
import ru.graduation.service.UserService;

import java.net.URI;
import java.util.List;

import static ru.graduation.util.ValidationUtil.*;

@RestController
@RequestMapping(AdminRestController.ADMIN_URL)
public class AdminRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public static final String ADMIN_URL="/admin/users";

    @Autowired
    private UserService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@RequestBody User user){
        log.info("create {}",user);
        checkNew(user);
        User created=service.create(user);
        URI uriOfNewResorces= ServletUriComponentsBuilder.fromCurrentContextPath()
                .buildAndExpand(AdminRestController.ADMIN_URL+"/{id}",created.getId()).toUri();
        return  ResponseEntity.created(uriOfNewResorces).body(created);
    }

    @PutMapping(value="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user,@PathVariable int id){
        log.info("update {}",id);
        assureIdConsistent(user,id);
        service.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        log.info("update {}",id);
        service.delete(id);
    }

    @GetMapping
    public List<User> getAll(){
        log.info("getAll {}");
       return service.getAll();
    }

}
