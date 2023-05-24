package com.example.nbudeanski.spacex_api.services;

import com.example.nbudeanski.spacex_api.DTO.RocketDTO;

import com.example.nbudeanski.spacex_api.exceptions.NoSuchRocketException;
import com.example.nbudeanski.spacex_api.model.Rocket;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class RocketService {

    private final WebClient webClient;
    private final ModelMapper modelMapper;

    @Autowired
    public RocketService(WebClient webClient, ModelMapper modelMapper) {
        this.webClient = webClient;
        this.modelMapper = modelMapper;
    }

    public List<RocketDTO> retrieveAll() {
        return webClient.get()
                .uri("https://api.spacexdata.com/v4/rockets")
                .retrieve()
                .bodyToFlux(Rocket.class)
                .map(this::convertToDTO)
                .collectList()
                .block();
    }

    public RocketDTO retrieveOne(String id) {
        try {
            return webClient.get()
                    .uri("https://api.spacexdata.com/v4/rockets/" + id)
                    .retrieve()
                    .bodyToFlux(Rocket.class)
                    .map(this::convertToDTO)
                    .blockFirst();
        }
        catch (WebClientResponseException ex){
            throw new NoSuchRocketException("There is no rocket with such ID");
        }
    }

    public RocketDTO retrieveOneByName(String name) {
        List<RocketDTO> rockets = retrieveAll();
        for(RocketDTO r : rockets){
            String n = r.getName().toLowerCase().replaceAll("\\s", "");
            if (name.equals(n)) return r;
        }
        throw new NoSuchRocketException("There is no such rocket with name you typed !");
    }

    private RocketDTO convertToDTO(Rocket rocket){
        return modelMapper.map(rocket, RocketDTO.class);
    }
}
