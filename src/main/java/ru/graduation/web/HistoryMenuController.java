package ru.graduation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.graduation.model.Dish;
import ru.graduation.model.HistoryMenu;
import ru.graduation.repository.datajpa.CrudHistoryMenuRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value=HistoryMenuController.REST_URL,produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoryMenuController {

    private static final Sort SORT_DATE=new Sort(Sort.Direction.DESC,"date","name");

    public static final String REST_URL="/profile/history/menu";

    @Autowired
    private CrudHistoryMenuRepository repository;

    @GetMapping
    public List<HistoryMenu> getHistoryMenu(){
        return repository.findAll(SORT_DATE);
    }

    @GetMapping("/{date}")
    public List<HistoryMenu> getHistoryMenuByDate(@PathVariable LocalDate date){
        return repository.getMenuByDate(date);
    }


}
