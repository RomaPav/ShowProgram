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
    @RequestMapping()
    public List<Show> getShows(){
        return service.getAll();
    }
    @RequestMapping("hello")
    public String sayHello(){
        return "Hello world";
    }
    @RequestMapping("{id}")
    Show showOne(@PathVariable String id) {
        return service.get(id);
    }
    @DeleteMapping("{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }
    @PostMapping("create")
    Show create(@RequestBody Show show) {
        service.create(show);
        return  show;
    }
    @PutMapping("/update")
    Show update(@RequestBody Show show) {
        service.update(show);
        return  show;
    }
}
