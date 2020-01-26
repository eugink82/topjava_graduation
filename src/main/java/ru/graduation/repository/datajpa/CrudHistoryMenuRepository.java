package ru.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.graduation.model.HistoryMenu;

import java.time.LocalDate;
import java.util.List;

public interface CrudHistoryMenuRepository extends JpaRepository<HistoryMenu,Integer> {

    @Query("select h from HistoryMenu h where h.date=?1")
    public List<HistoryMenu> findAllMenuByDate(LocalDate date);

    @Query("select h from HistoryMenu h order by h.date desc,h.name asc")
    public List<HistoryMenu> findAll();
}
