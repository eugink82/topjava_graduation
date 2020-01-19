package ru.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Restaurant;
import ru.graduation.service.RestaurantService;

import java.net.URI;
import java.util.List;

import static ru.graduation.util.ValidationUtil.assureIdConsistent;
import static ru.graduation.util.ValidationUtil.checkNew;


@RestController
@RequestMapping(value=RestaurantAdminController.REST_URL,produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public static final String REST_URL="/admin/restaurants";



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant){
        log.info("create {}",restaurant);
        checkNew(restaurant);
        Restaurant createdRestaurant=service.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(createdRestaurant.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(createdRestaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        log.info("delete {}", id);
        service.delete(id);
    }

    @PutMapping(value="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody Restaurant restaurant){
        log.info("update {}", id);
        assureIdConsistent(restaurant, id);
        service.update(restaurant);
    }


}
