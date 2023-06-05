package com.example.nbudeanski.spacex_api.services;

import com.example.nbudeanski.spacex_api.DTO.RocketDTO;

import com.example.nbudeanski.spacex_api.exceptions.InvalidFilterSearchException;
import com.example.nbudeanski.spacex_api.exceptions.InvalidSortingConditionException;
import com.example.nbudeanski.spacex_api.exceptions.InvalidSortingOrderException;
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
        refreshDatabase();
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


    public List<RocketDTO> retrieveWithDoubleFilterCondition(Double minHeight, Double maxHeight, Double minDiameter, Double maxDiameter, Long minCostPerLaunch, Long maxCostPerLaunch, Integer minMass, Integer maxMass, String minFirstFlightDate, String maxFirstFlightDate) {
        refreshDatabase();
        if (minHeight != null)
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByHeightBetween(minHeight, maxHeight));
        else if(minDiameter != null)
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByDiameterBetween(minDiameter, maxDiameter));
        else if (minCostPerLaunch != null)
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByCostPerLaunchBetween(minCostPerLaunch, maxCostPerLaunch));
        else if (minMass != null)
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByMassBetween(minMass, maxMass));
        else if(minFirstFlightDate != null){
            LocalDate firstFlightMin = LocalDate.parse(minFirstFlightDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate firstFlightMax = LocalDate.parse(maxFirstFlightDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByFirstFlightBetween(firstFlightMin, firstFlightMax));
        }

        throw new InvalidFilterSearchException();
    }

    public List<RocketDTO> retrieveWithSingleFilterCondition(Double minHeight, Double maxHeight, Double minDiameter, Double maxDiameter, Long minCostPerLaunch, Long maxCostPerLaunch, Integer minMass, Integer maxMass, String minFirstFlightDate, String maxFirstFlightDate) {
        refreshDatabase();
        if (minHeight != null)
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByHeightGreaterThan(minHeight));
        else if(maxHeight != null)
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByHeightLowerThan(maxHeight));
        else if(minDiameter != null)
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByDiameterGreaterThan(minDiameter));
        else if (maxDiameter != null)
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByDiameterLowerThan(maxDiameter));
        else if(minCostPerLaunch != null)
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByCostPerLaunchGreaterThan(minCostPerLaunch));
        else if(maxCostPerLaunch != null)
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByCostPerLaunchLowerThan(maxCostPerLaunch));
        else if(minMass != null)
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByMassGreaterThan(minMass));
        else if(maxMass != null)
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByMassLowerThan(maxMass));
        else if(minFirstFlightDate != null) {
            LocalDate firstFlightMin = LocalDate.parse(minFirstFlightDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByFirstFlightAfter(firstFlightMin));
        }
        else if(maxFirstFlightDate != null) {
            LocalDate firstFlightMax = LocalDate.parse(maxFirstFlightDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return objectMapper.convertListOfEntitiesToDTO
                    (rocketRepository.findByFirstFlightBefore(firstFlightMax));
        }

        throw new InvalidFilterSearchException();
    }

    public List<RocketDTO> retrieveAllBySortCondition(String sortingCondition, String order) {
        if (order == null) {
            switch (sortingCondition) {
                case "height" -> {
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderedByHeightAsc());
                }
                case "diameter" -> {
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderedByDiameterAsc());
                }
                case "mass" -> {
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderedByMassAsc());
                }
                case "costPerLaunch" -> {
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderedByCostPerLaunchAsc());
                }
                case "firstFlight" -> {
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderByFirstFlightAsc());
                }
            }
        }
        else {
            if (!order.equals("asc") && !order.equals("desc"))
                throw new InvalidSortingOrderException(order);
            if (sortingCondition.equals("height")) {
                if (order.equals("asc"))
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderedByHeightAsc());
                else
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderedByHeightDesc());

            }
            else if (sortingCondition.equals("diameter")) {
                if (order.equals("asc"))
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderedByDiameterAsc());
                else
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderedByDiameterDesc());
            }
            else if (sortingCondition.equals("mass")) {
                if (order.equals("asc"))
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderedByMassAsc());
                else
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderedByMassDesc());
            }
            else if (sortingCondition.equals("costPerLaunch")) {
                if (order.equals("asc"))
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderedByCostPerLaunchAsc());
                else
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderedByCostPerLaunchDesc());
            }
            else if (sortingCondition.equals("firstFlight")) {
                if (order.equals("asc"))
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderByFirstFlightAsc());
                else
                    return objectMapper.convertListOfEntitiesToDTO
                            (rocketRepository.findAllOrderByFirstFlightDesc());
            }
        }

        throw new InvalidSortingConditionException(sortingCondition);
    }


    private void refreshDatabase() {
        List<Rocket> rockets = spaceXClient.getAllRocketsFromAPI();
        for (Rocket r : rockets){
            Optional<RocketEntity> entity = rocketRepository.findByRocketId(r.getRocket_id());
            if (entity.isEmpty()){
                rocketRepository.save(objectMapper.convertRocketToEntity(r));
            }
        }
    }

}
