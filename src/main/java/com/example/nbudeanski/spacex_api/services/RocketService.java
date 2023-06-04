package com.example.nbudeanski.spacex_api.services;

import com.example.nbudeanski.spacex_api.DTO.RocketDTO;

import com.example.nbudeanski.spacex_api.exceptions.NoSuchRocketException;
import com.example.nbudeanski.spacex_api.model.api.*;
import com.example.nbudeanski.spacex_api.model.entity.*;
import com.example.nbudeanski.spacex_api.repository.RocketRepository;
import com.example.nbudeanski.spacex_api.util.ObjectMapperCustom;
import com.example.nbudeanski.spacex_api.util.SpaceXClient;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RocketService {

    private final RocketRepository rocketRepository;
    private final SpaceXClient spaceXClient;
    private final ObjectMapperCustom objectMapper;

    @Autowired
    public RocketService(RocketRepository rocketRepository, SpaceXClient spaceXClient, ObjectMapperCustom objectMapper) {
        this.rocketRepository = rocketRepository;
        this.spaceXClient = spaceXClient;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public List<RocketDTO> retrieveAll() {
        List<Rocket> rockets = spaceXClient.getAllRocketsFromAPI();
        for (Rocket r : rockets){
            Optional<RocketEntity> entity = rocketRepository.findByRocketId(r.getRocket_id());
            if (entity.isEmpty()){
                rocketRepository.save(objectMapper.convertRocketToEntity(r));
            }
        }
        return rocketRepository.findAll().stream().map(objectMapper::convertEntityToDTO).collect(Collectors.toList());
    }

    @Transactional
    public RocketDTO retrieveOne(String id) {
        try {
            Optional<RocketEntity> entity = rocketRepository.findByRocketId(id);

            if (entity.isEmpty()){
                Rocket rocket = spaceXClient.getRocketFromAPI(id);
                RocketEntity rocketEntity = objectMapper.convertRocketToEntity(rocket);
                rocketRepository.save(rocketEntity);
                return objectMapper.convertEntityToDTO(rocketEntity);
            }

            return  objectMapper.convertEntityToDTO(entity.get());
        }
        catch (WebClientResponseException ex){
            throw new NoSuchRocketException("There is no rocket with such ID");
        }
    }

    public RocketDTO retrieveOneByName(String name) {
        Optional<RocketEntity> entity = rocketRepository.findByNameIgnoreCase(name);

        if (entity.isEmpty()) {
            List<RocketDTO> rockets = retrieveAll();
            for (RocketDTO r : rockets) {
                String n = r.getName().toLowerCase().replaceAll("\\s", "");
                if (name.equals(n)) return r;
            }
        }
        else return objectMapper.convertEntityToDTO(entity.get());

        throw new NoSuchRocketException("There is no such rocket with name you typed !");
    }
}
