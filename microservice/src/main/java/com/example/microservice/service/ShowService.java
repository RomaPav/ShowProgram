package com.example.microservice.service;

import com.example.microservice.model.Show;
import com.example.microservice.repository.ShowRepository;
//import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
//    private List<Show> shows = new ArrayList<>();

    private ShowRepository repository;

    @Autowired
    public ShowService(ShowRepository repository) {
        this.repository = repository;
    }

    //    @PostConstruct
//    void init(){
//        shows.add(new Show("1","Голос Країни", "Конкурс співаків"));
//        shows.add(new Show("2","Україна має талант ", "Талант шоу"));
//        shows.add(new Show("3","Майстер шеф діти", "Кухонна телебачення"));
//
//        repository.saveAll(shows);
//    }
    public List<Show> getAll() {
        return repository.findAll();
    }

    public Show get(String id) {
        return repository.findById(id)
                .orElse(null);
    }

    public Show create(Show show) {
        show.setCreatedAt(LocalDateTime.now());
        return repository.save(show);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Show update(Show show) {
        show.setUpdatedAt(LocalDateTime.now());
        return repository.save(show);
    }
}
