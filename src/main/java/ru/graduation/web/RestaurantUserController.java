package ru.graduation.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.graduation.model.Restaurant;
import ru.graduation.service.RestaurantService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value=RestaurantUserController.REST_URL,produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUserController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public static final String REST_URL="/restaurants";

    @GetMapping
    public List<Restaurant> getAll(@RequestParam(required = false) boolean withMenu){
        List<Restaurant> restaurants=service.getAll();
        if(withMenu){
            return restaurants.stream()
                    .map(r->service.getWithDishes(r.getId()))
                    .collect(Collectors.toList());
        }
        log.info("getAll");
        return restaurants;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id, @RequestParam(required = false) boolean withMenu){
       return withMenu ? service.getWithDishes(id) : service.get(id);
    }

    @GetMapping("/by")
    public Restaurant getByName(@RequestParam String name, @RequestParam(required = false) boolean withMenu){
        Restaurant restaurant=service.getByName(name);
        return withMenu ? service.getWithDishes(restaurant.getId()) : restaurant;
    }


}
