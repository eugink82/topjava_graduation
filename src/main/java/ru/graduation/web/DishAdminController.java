package ru.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Dish;
import ru.graduation.service.DishService;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.graduation.util.ValidationUtil.*;

@RestController
@RequestMapping(value=DishAdminController.REST_URL)
public class DishAdminController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    public static final String REST_URL="/admin/restaurants/{id}/dishes";

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocations(@RequestBody Dish dish, @PathVariable int id){
       log.info("create {}",dish);
        checkNew(dish);
        Dish created=service.create(dish,id);
       URI uriOfNewResource= ServletUriComponentsBuilder.fromCurrentContextPath()
       .path(REST_URL+"/{newId}").buildAndExpand(id,created.getId()).toUri();
       return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int dishId, @PathVariable int id){
        log.info("create {}",dishId);
        service.delete(dishId,id);
    }

    @PutMapping("/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish,@PathVariable int dishId, @PathVariable int id){
        log.info("update {}",dish);
        assureIdConsistent(dish,dishId);
        service.update(dish,id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getMenuByDate(@RequestParam LocalDate dateMenu, @PathVariable int id){
//        log.info("getMenu {} by date{ }",dish);
        return service.getMenu(dateMenu,id);
    }


}
