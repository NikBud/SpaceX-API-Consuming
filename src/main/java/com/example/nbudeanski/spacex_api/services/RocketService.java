package com.example.nbudeanski.spacex_api.services;

import com.example.nbudeanski.spacex_api.DTO.RocketDTO;

import com.example.nbudeanski.spacex_api.exceptions.NoSuchRocketException;
import com.example.nbudeanski.spacex_api.model.api.*;
import com.example.nbudeanski.spacex_api.model.api.secondStage.SecondStage;
import com.example.nbudeanski.spacex_api.model.entity.*;
import com.example.nbudeanski.spacex_api.model.entity.secondStage.PayloadsEntity;
import com.example.nbudeanski.spacex_api.model.entity.secondStage.SecondStageEntity;
import com.example.nbudeanski.spacex_api.model.entity.secondStage.ThrustEntity;
import com.example.nbudeanski.spacex_api.repository.RocketRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RocketService {

    private final WebClient webClient;
    private final ModelMapper modelMapper;

    private final RocketRepository rocketRepository;

    @Autowired
    public RocketService(WebClient webClient, ModelMapper modelMapper, RocketRepository rocketRepository) {
        this.webClient = webClient;
        this.modelMapper = modelMapper;
        this.rocketRepository = rocketRepository;
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
            Rocket rocket = webClient.get()
                    .uri("https://api.spacexdata.com/v4/rockets/" + id)
                    .retrieve()
                    .bodyToFlux(Rocket.class)
                    .blockFirst();

            Converter<List<String>, List<FlickrImage>> flickrImageConverter = context -> context.getSource().stream()
                .map(str -> new FlickrImage(str)).collect(Collectors.toList());

            Converter<List<PayloadWeight>, List<PayloadWeightEntity>> payloadWeightConverter = context -> context.getSource().stream()
                            .map(jsonObj -> new PayloadWeightEntity(jsonObj.getPayloadWeightId(), jsonObj.getName(), jsonObj.getKg(), jsonObj.getLb()))
                    .collect(Collectors.toList());

//            modelMapper.createTypeMap(Rocket.class, RocketEntity.class)
//                    .addMappings(mapper -> mapper
//                        .using(flickrImageConverter)
//                            .map(Rocket::getFlickrImages, RocketEntity::setFlickrImages))
//                    .addMappings(mapper -> mapper
//                            .using(payloadWeightConverter)
//                                .map(Rocket::getPayloadWeights, RocketEntity::setPayloadWeights));


            RocketEntity r = modelMapper.map(rocket, RocketEntity.class);
            System.out.println(r);

            return convertToDTO(rocket);
        }
        catch (WebClientResponseException ex){
            throw new NoSuchRocketException("There is no rocket with such ID");
        }
    }

    @Transactional
    public RocketEntity retrieveOneToEntity(String id) {
        try {
            Rocket rocket = webClient.get()
                    .uri("https://api.spacexdata.com/v4/rockets/" + id)
                    .retrieve()
                    .bodyToFlux(Rocket.class)
                    .blockFirst();

            Converter<List<String>, List<FlickrImage>> flickrImageConverter = context -> context.getSource().stream()
                    .map(str -> new FlickrImage(str)).collect(Collectors.toList());

            Converter<List<PayloadWeight>, List<PayloadWeightEntity>> payloadWeightConverter = context -> context.getSource().stream()
                    .map(jsonObj -> new PayloadWeightEntity(jsonObj.getPayloadWeightId(), jsonObj.getName(), jsonObj.getKg(), jsonObj.getLb()))
                    .collect(Collectors.toList());

            Converter<ThrustSeaLevel, ThrustSeaLevelFirstStage> thrustSeaLevelConverter = context -> {
                ThrustSeaLevel thrustSeaLevel = context.getSource();
                ThrustSeaLevelFirstStage firstStage = new ThrustSeaLevelFirstStage();
                firstStage.setkN(thrustSeaLevel.getkN());
                firstStage.setLbf(thrustSeaLevel.getLbf());
                return firstStage;
            };

            Converter<ThrustVacuum, ThrustVacuumFirstStage> thrustVacuumConverter = context -> {
                ThrustVacuumFirstStage thrustVacuum = new ThrustVacuumFirstStage();
                thrustVacuum.setkN(context.getSource().getkN());
                thrustVacuum.setLbf(context.getSource().getLbf());
                return thrustVacuum;
            };

            Converter<Height, HeightCompositeFairing> heightConverter = context -> {
                Height sourceHeight = context.getSource();
                HeightCompositeFairing heightCompositeFairing = new HeightCompositeFairing();
                heightCompositeFairing.setMeters(sourceHeight.getMeters());
                heightCompositeFairing.setFeet(sourceHeight.getFeet());
                return heightCompositeFairing;
            };

            Converter<Diameter, DiameterCompositeFairing> diameterConverter = context -> {
                DiameterCompositeFairing diameter = new DiameterCompositeFairing();
                diameter.setFeet(context.getSource().getFeet());
                diameter.setMeters(context.getSource().getMeters());
                return diameter;
            };

            Converter<ThrustSeaLevel, ThrustSeaLevelEngines> thrustSeaLevelEnginesConverter = context -> {
                ThrustSeaLevelEngines thrustSeaLevel = new ThrustSeaLevelEngines();
                thrustSeaLevel.setkN(context.getSource().getkN());
                thrustSeaLevel.setLbf(context.getSource().getLbf());
                return thrustSeaLevel;
            };

            Converter<ThrustVacuum, ThrustVacuumEngines> thrustVacuumEnginesConverter = context -> {
                ThrustVacuumEngines thrustVacuum = new ThrustVacuumEngines();
                thrustVacuum.setkN(context.getSource().getkN());
                thrustVacuum.setLbf(context.getSource().getLbf());
                return thrustVacuum;
            };

            Converter<SecondStage, SecondStageEntity> secondStageConverter = context -> {
                SecondStage source = context.getSource();
                SecondStageEntity secondStage = new SecondStageEntity();
                secondStage.setEngines(source.getEngines());
                secondStage.setPayloads(modelMapper.map(source.getPayloads(), PayloadsEntity.class));
                secondStage.setThrust(modelMapper.map(source.getThrust(), ThrustEntity.class));
                secondStage.setEngines(source.getEngines());
                secondStage.setReusable(source.isReusable());
                secondStage.setBurnTimeSec(source.getBurnTimeSec());
                secondStage.setFuelAmountTons(source.getFuelAmountTons());
                return secondStage;
            };



            modelMapper.createTypeMap(Rocket.class, RocketEntity.class)
                    .addMappings(mapper -> mapper
                            .using(thrustSeaLevelConverter)
                            .map(src -> src.getFirstStage().getThrustSeaLevel(),
                                    (dst,value) -> dst.getFirstStage().setThrustSeaLevel((ThrustSeaLevelFirstStage) value)))
                    .addMappings(mapper -> mapper
                            .using(thrustVacuumConverter)
                            .map(src -> src.getFirstStage().getThrusVacuum(),
                                    (dst, value) -> dst.getFirstStage().setThrustVacuum((ThrustVacuumFirstStage) value)))
                    .addMappings(mapper -> mapper
                            .using(heightConverter)
                            .map(src -> src.getSecondStage().getPayloads().getCompositeFairing().getHeight(),
                                    (dst, value) -> dst.getSecondStage().getPayloads().getCompositeFairing().setHeight((HeightCompositeFairing) value)))
                    .addMappings(mapper -> mapper
                            .using(diameterConverter)
                            .map(src -> src.getSecondStage().getPayloads().getCompositeFairing().getDiameter(),
                                    (dst, value) -> dst.getSecondStage().getPayloads().getCompositeFairing().setDiameter((DiameterCompositeFairing) value)))
                    .addMappings(mapping -> mapping
                            .using(secondStageConverter)
                            .map(Rocket::getSecondStage, RocketEntity::setSecondStage))
                    .addMappings(mapper -> mapper
                            .using(thrustSeaLevelEnginesConverter)
                            .map(src -> src.getEngines().getThrustSeaLevel(),
                                    (dst, value) -> dst.getEngines().setThrustSeaLevel((ThrustSeaLevelEngines) value)))
                    .addMappings(mapper -> mapper
                            .using(thrustVacuumEnginesConverter)
                            .map(src -> src.getEngines().getThrustVacuum(),
                                    (dst, value) -> dst.getEngines().setThrustVacuum((ThrustVacuumEngines) value)))
                    .addMappings(mapper -> mapper
                            .using(flickrImageConverter)
                            .map(Rocket::getFlickrImages, RocketEntity::setFlickrImages))
                    .addMappings(mapper -> mapper
                            .using(payloadWeightConverter)
                            .map(Rocket::getPayloadWeights, RocketEntity::setPayloadWeights));


            RocketEntity r = modelMapper.map(rocket, RocketEntity.class);
//            r.getFlickrImages().forEach(flickrImage -> flickrImage.setRocket(r));
//            r.getPayloadWeights().forEach(payloadWeight -> payloadWeight.setRocket(r));

            rocketRepository.save(r);
            System.out.println("created!");
            System.out.println(r);

            return rocketRepository.findById(Long.valueOf(1)).get();
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
