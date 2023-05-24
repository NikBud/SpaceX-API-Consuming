package com.example.nbudeanski.spacex_api.controllers;

import com.example.nbudeanski.spacex_api.DTO.RocketDTO;
import com.example.nbudeanski.spacex_api.services.RocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/spaceX/rockets")
public class RocketController {

    private final RocketService rocketService;

    @Autowired
    public RocketController(RocketService rocketService) {
        this.rocketService = rocketService;
    }

    @GetMapping("")
    public List<RocketDTO> getAll() {
        return rocketService.retrieveAll();
    }

    @GetMapping("/{id}")
    public RocketDTO getOne(@PathVariable("id") String id) {
        return rocketService.retrieveOne(id);
    }

    @GetMapping("/byName/{name}")
    public RocketDTO getOneByName(@PathVariable("name") String name) {
        return rocketService.retrieveOneByName(name);
    }

}
