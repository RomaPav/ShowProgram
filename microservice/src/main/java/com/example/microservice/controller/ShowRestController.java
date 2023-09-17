package com.example.microservice.controller;

import com.example.microservice.model.Show;
import com.example.microservice.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/show/")
public class ShowRestController {
    @Autowired
    ShowService service;
    @RequestMapping("hello")
    public String sayHello(){
        return "Hello world";
    }
    @RequestMapping()
    public List<Show> getShows(){
        return service.getAll();
    }
    @RequestMapping("{id}")
    Show showOne(@PathVariable String id) {
        return service.get(id);
    }
    @DeleteMapping("{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }
    @PostMapping("")
    Show create(@RequestBody Show show) {return service.create(show);}
    @PutMapping("")
    Show update(@RequestBody Show show) {return  service.update(show);}
}
