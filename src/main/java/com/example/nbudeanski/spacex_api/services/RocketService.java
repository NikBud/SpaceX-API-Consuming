package com.example.nbudeanski.spacex_api.services;

import com.example.nbudeanski.spacex_api.DTO.RocketDTO;

import com.example.nbudeanski.spacex_api.exceptions.InvalidSortingConditionException;
import com.example.nbudeanski.spacex_api.exceptions.NoSuchRocketException;
import com.example.nbudeanski.spacex_api.model.api.*;
import com.example.nbudeanski.spacex_api.model.entity.*;
import com.example.nbudeanski.spacex_api.repository.RocketRepository;
import com.example.nbudeanski.spacex_api.util.ObjectMapperCustom;
import com.example.nbudeanski.spacex_api.util.SpaceXClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
        refreshDatabase();
        return rocketRepository.findAll().stream().map(objectMapper::convertEntityToDTO).collect(Collectors.toList());
    }

    @Transactional
    public RocketDTO retrieveOne(String id) {
        refreshDatabase();
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

    public List<RocketDTO> getAllWithOrWithoutFilterConditionsAndSortConditions(Double minHeight, Double maxHeight, Double minDiameter, Double maxDiameter, Long minCostPerLaunch, Long maxCostPerLaunch, Integer minMass, Integer maxMass, String minFirstFlightDate, String maxFirstFlightDate, String sortingCondition, String order){
        refreshDatabase();

        LocalDate firstFlightMin = null;
        LocalDate firstFlightMax = null;
        if (minFirstFlightDate != null)
            firstFlightMin = LocalDate.parse(minFirstFlightDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (maxFirstFlightDate != null)
            firstFlightMax = LocalDate.parse(maxFirstFlightDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        List<RocketEntity> filteredEntities = rocketRepository.filteringSearch(minHeight, maxHeight, minDiameter, maxDiameter, minCostPerLaunch, maxCostPerLaunch, minMass, maxMass, firstFlightMin, firstFlightMax);
        if (sortingCondition != null){
            return retrieveAllBySortCondition(sortingCondition, order, filteredEntities);
        }
        else {
            return objectMapper.convertListOfEntitiesToDTO(filteredEntities);
        }
    }

    public List<RocketDTO> retrieveAllBySortCondition(String sortingCondition, String order, List<RocketEntity> entities) {
        Comparator<RocketEntity> comparator =
        switch (sortingCondition) {
            case "height" -> Comparator.comparing(RocketEntity::getHeight, HeightRocket::compareTo);
            case "diameter" -> Comparator.comparing(RocketEntity::getDiameter, DiameterRocket::compareTo);
            case "mass" -> Comparator.comparing(RocketEntity::getMass, MassEntity::compareTo);
            case "costPerLaunch" -> Comparator.comparing(RocketEntity::getCostPerLaunch);
            case "firstFlight" -> Comparator.comparing(RocketEntity::getFirstFlight);
            default -> throw new InvalidSortingConditionException(sortingCondition);
        };

        if (order != null && order.equals("desc")) {
            comparator = comparator.reversed();
        }
        else if (order != null && !order.equals("asc")){
            throw new InvalidSortingConditionException(sortingCondition);
        }

        return entities.stream()
                .sorted(comparator)
                .map(objectMapper::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void refreshDatabase() {
        List<Rocket> rockets = spaceXClient.getAllRocketsFromAPI();
        for (Rocket r : rockets){
            Optional<RocketEntity> entity = rocketRepository.findByRocketId(r.getRocket_id());
            if (entity.isEmpty()){
                rocketRepository.save(objectMapper.convertRocketToEntity(r));
            }
        }
    }

}
