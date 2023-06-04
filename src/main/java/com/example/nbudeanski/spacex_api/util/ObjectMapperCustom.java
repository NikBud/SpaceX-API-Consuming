package com.example.nbudeanski.spacex_api.util;

import com.example.nbudeanski.spacex_api.DTO.RocketDTO;
import com.example.nbudeanski.spacex_api.model.api.PayloadWeight;
import com.example.nbudeanski.spacex_api.model.api.Rocket;
import com.example.nbudeanski.spacex_api.model.api.ThrustSeaLevel;
import com.example.nbudeanski.spacex_api.model.api.ThrustVacuum;
import com.example.nbudeanski.spacex_api.model.api.secondStage.SecondStage;
import com.example.nbudeanski.spacex_api.model.entity.*;
import com.example.nbudeanski.spacex_api.model.entity.secondStage.PayloadsEntity;
import com.example.nbudeanski.spacex_api.model.entity.secondStage.SecondStageEntity;
import com.example.nbudeanski.spacex_api.model.entity.secondStage.ThrustEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectMapperCustom {
    private final ModelMapper modelMapper;

    @Autowired
    public ObjectMapperCustom(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapperSetupEntityToDTO();
        modelMapperSetupRocketToEntity();
        modelMapperSetupRocketToDTO();
    }

    public RocketDTO convertRocketToDTO(Rocket rocket){
        return modelMapper.map(rocket, RocketDTO.class);
    }

    public RocketEntity convertRocketToEntity(Rocket rocket) {
        RocketEntity r = modelMapper.map(rocket, RocketEntity.class);

        r.getHeight().setRocket(r);
        r.getDiameter().setRocket(r);
        r.getMass().setRocket(r);
        r.getSecondStage().getPayloads().getCompositeFairing().setPayloads(r.getSecondStage().getPayloads());
        r.getLandingLegs().setRocket(r);

        return r;
    }

    public RocketDTO convertEntityToDTO (RocketEntity rocketEntity){
        return modelMapper.map(rocketEntity, RocketDTO.class);
    }

    private void modelMapperSetupEntityToDTO() {

        Converter<ThrustVacuumFirstStage, ThrustVacuum> thrustVacuumFirstStageThrustVacuumConverter = context -> {
            ThrustVacuum thrustVacuum = new ThrustVacuum();
            thrustVacuum.setkN(context.getSource().getkN());
            thrustVacuum.setLbf(context.getSource().getLbf());
            return thrustVacuum;
        };

        modelMapper.createTypeMap(RocketEntity.class, RocketDTO.class)
                .addMappings(mapper -> mapper
                        .using(thrustVacuumFirstStageThrustVacuumConverter)
                        .map(src -> src.getFirstStage().getThrustVacuum(), (dst, value) -> dst.getFirstStage().setThrustVacuum((ThrustVacuum) value)))
                .addMappings(mapper -> mapper
                        .map(RocketEntity::getRocket_id, RocketDTO::setId))
                .addMappings(mapper -> mapper
                        .map(RocketEntity::getFirstFlight, RocketDTO::setFirstFlight));
    }

    private void modelMapperSetupRocketToDTO() {
        modelMapper.createTypeMap(Rocket.class, RocketDTO.class)
                .addMappings(mapper -> mapper
                        .map(src -> src.getRocket_id(), (dst, value) -> dst.setId((String) value)));
    }

    private void modelMapperSetupRocketToEntity() {
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
                        .map(src -> src.getFirstStage().getThrustVacuum(),
                                (dst, value) -> dst.getFirstStage().setThrustVacuum((ThrustVacuumFirstStage) value)))
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


    }

}
