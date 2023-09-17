package com.example.microservice.service;

import com.example.microservice.model.Show;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    private List<Show> shows = new ArrayList<>();

    @PostConstruct
    void init(){
        shows.add(new Show("1","Голос Країни", "Конкурс співаків"));
        shows.add(new Show("2","Україна має талант ", "Талант шоу"));
        shows.add(new Show("3","Майстер шеф діти", "Кухонна телебачення"));
    }

    public List<Show> getAll()
    {
        return shows;
    }

    public Show get(String id) {
        return shows.stream().filter(item->item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public void delete(String id) {
        shows=shows.stream().filter(item-> !item.getId().equals(id))
                .toList();
    }
    public void create(Show show){
        shows.add(show);
    }
    public void update(Show show){
        shows=shows.stream().map(item-> {
            if (item.getId().equals(show.getId())){
                item=show;
                return item;
            }
            return item;
        }).toList();
    }
}
