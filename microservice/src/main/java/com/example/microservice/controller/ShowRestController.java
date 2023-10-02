package com.example.microservice.controller;

import com.example.microservice.model.Show;
import com.example.microservice.service.ShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/show/")
@Tag(name = "Shows controller", description = "CRUD realisation")
public class ShowRestController {
    @Autowired
    ShowService service;
    @Operation(summary = "Get congratulations", description  = "Get text of congratulations 'Hello world'")
    @GetMapping("hello")
    public String sayHello(){
        return "Hello world";
    }
    @Operation(summary = "Get all Show", description  = "return {\n" +
            "    \"id\": \"65116b70e2fd1d1fc17476ee\",\n" +
            "    \"name\": \"Майстер шеф\",\n" +
            "    \"description\": \"Кухонна телебачення1\",\n" +
            "    \"createdAt\": \"2023-09-25T14:13:52.837\",\n" +
            "    \"updatedAt\": \"2023-09-25T14:13:56.239\"\n" +
            "  }")
    @GetMapping()
    public List<Show> getShows(){
        return service.getAll();
    }
    @Operation(summary = "Get one Show", description  = "return {\n" +
            "    \"id\": \"65116b70e2fd1d1fc17476ee\",\n" +
            "    \"name\": \"Майстер шеф\",\n" +
            "    \"description\": \"Кухонна телебачення1\",\n" +
            "    \"createdAt\": \"2023-09-25T14:13:52.837\",\n" +
            "    \"updatedAt\": \"2023-09-25T14:13:56.239\"\n" +
            "  }")
    @GetMapping("{id}")
    Show showOne(@PathVariable String id) {
        return service.get(id);
    }
    @Operation(summary = "Delete one Show", description  = "Delete item by id")
    @DeleteMapping("{id}")
    void deleteOne(@PathVariable String id) {
        service.delete(id);
    }
    @Operation(summary = "Create one Show", description  = "Create show by sendinc object looks like {\n" +
            "    \"name\": \"Майстер шеф\",\n" +
            "    \"description\": \"Кухонна телебачення1\",\n" +
            "  }")
    @PostMapping("")
    Show create(@RequestBody Show show) {return service.create(show);}
    @Operation(summary = "Update one Show", description  = "Updating a show by passing an updated object looks like {\n" +
            "    \"id\": \"3\",\n" +
            "    \"name\": \"Майстер шеф діти \",\n" +
            "    \"description\": \"Кухонна телебачення\",\n" +
            "  }")
    @PutMapping("")
    Show update(@RequestBody Show show) {return  service.update(show);}
}
