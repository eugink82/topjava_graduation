package ru.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.graduation.model.HistoryMenu;
import ru.graduation.repository.datajpa.CrudHistoryMenuRepository;
import ru.graduation.to.DishTo;
import ru.graduation.to.HistoryMenuTo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HistoryMenuService {

    @Autowired
    private CrudHistoryMenuRepository repository;

    public List<HistoryMenuTo> getAllHistoryMenus() {
        List<HistoryMenuTo> myListHistoryTo = new ArrayList<>();
        List<HistoryMenu> rawHistoryMenu = repository.findAll();
        return getRestaurantMenuHistory(myListHistoryTo, rawHistoryMenu);
    }

    public List<HistoryMenuTo> getHistoryMenuByDate(LocalDate localDate) {
        List<HistoryMenuTo> myListHistoryTo = new ArrayList<>();
        List<HistoryMenu> rawHistoryMenu = repository.findAllMenuByDate(localDate);
        return getRestaurantMenuHistory(myListHistoryTo, rawHistoryMenu);
    }

    private List<HistoryMenuTo> getRestaurantMenuHistory(List<HistoryMenuTo> myListHistoryTo, List<HistoryMenu> rawHistoryMenu) {
        Map<LocalDate, Map<String, List<HistoryMenu>>> mapMenu = rawHistoryMenu.stream().
                collect(Collectors.groupingBy(HistoryMenu::getDate, Collectors.groupingBy(HistoryMenu::getName)));
        LocalDate date = null;
        for (Map.Entry<LocalDate, Map<String, List<HistoryMenu>>> entryR : mapMenu.entrySet()) {
            date = entryR.getKey();
            String name = "";
            for (Map.Entry<String, List<HistoryMenu>> entryDt : entryR.getValue().entrySet()) {
                name = entryDt.getKey();
                List<DishTo> listMenuTO = new ArrayList<>();
                for (HistoryMenu hm : entryDt.getValue()) {
                    listMenuTO.add(new DishTo(hm.getNameDish(), hm.getPrice()));
                }
                myListHistoryTo.add(new HistoryMenuTo(name, date, listMenuTO));
            }
        }
        return myListHistoryTo;
    }
}
