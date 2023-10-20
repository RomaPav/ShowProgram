package com.example.microservice.controller;

import com.example.microservice.model.Show;
import com.example.microservice.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui/v1/show/")
public class ShowGuiController {

    private ShowService service;
    @Autowired
    public ShowGuiController(ShowService service) {
        this.service = service;
    }

    @GetMapping()
    String showAll(Model model){
        model.addAttribute("items",service.getAll());
        return "show";
    }
    @RequestMapping("del/{id}")
    String delete(@PathVariable("id") String id){
        service.delete(id);
        return "redirect:/ui/v1/show/";
    }
    @PostMapping("edit/{id}")
    String edit(@PathVariable("id") String id,
                @RequestParam("name") String name,
                @RequestParam("description") String description){
        Show show=service.get(id);
        show.setName(name);
        show.setDescription(description);
        service.update(show);
        return "redirect:/ui/v1/show/";
    }
    @PostMapping("create")
    String create(@RequestParam("name") String name,
                @RequestParam("description") String description){
        Show show=new Show(name,description);
        service.create(show);
        return "redirect:/ui/v1/show/";
    }

}
