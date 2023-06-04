package com.example.nbudeanski.spacex_api.util;

import com.example.nbudeanski.spacex_api.model.api.Rocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class SpaceXClient {

    private final WebClient webClient;

    @Autowired
    public SpaceXClient(WebClient webClient){
        this.webClient = webClient;
    }

    public Rocket getRocketFromAPI(String id){
        return webClient.get()
                .uri("https://api.spacexdata.com/v4/rockets/" + id)
                .retrieve()
                .bodyToFlux(Rocket.class)
                .blockFirst();
    }

    public List<Rocket> getAllRocketsFromAPI() {
        return webClient.get()
                .uri("https://api.spacexdata.com/v4/rockets")
                .retrieve()
                .bodyToFlux(Rocket.class)
                .collectList()
                .block();
    }
}
