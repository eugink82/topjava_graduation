package ru.graduation.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.graduation.service.HistoryMenuService;
import ru.graduation.to.HistoryMenuTo;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value=HistoryMenuController.REST_URL,produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoryMenuController {



    public static final String REST_URL="/profile/history/menu";

    @Autowired
    private HistoryMenuService service;

    @GetMapping
    public List<HistoryMenuTo> getHistoryMenu(){
        return service.getAllHistoryMenus();
    }

    @GetMapping("/{date}")
    public List<HistoryMenuTo> getHistoryMenuByDate(@PathVariable LocalDate date){
        return service.getHistoryMenuByDate(date);
    }
}
