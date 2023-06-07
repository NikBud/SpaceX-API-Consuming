package com.example.nbudeanski.spacex_api.repository;

import com.example.nbudeanski.spacex_api.model.entity.RocketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RocketRepository extends JpaRepository<RocketEntity, Long> {

    @Query("SELECT r FROM RocketEntity r WHERE r.rocket_id = :id")
    Optional<RocketEntity> findByRocketId(String id);

    Optional<RocketEntity> findByNameIgnoreCase(String name);

    @Query("SELECT r FROM RocketEntity r WHERE (r.height.meters > :minHeight OR :minHeight IS NULL) AND (r.height.meters < :maxHeight OR :maxHeight IS NULL) " +
            "AND (r.diameter.meters > :minDiameter OR :minDiameter IS NULL) AND (r.diameter.meters < :maxDiameter OR :maxDiameter IS NULL)" +
            "AND (r.mass.kg > :minMass OR :minMass IS NULL) AND (r.mass.kg < :maxMass OR :maxMass IS NULL)" +
            "AND (r.costPerLaunch > :minCostPerLaunch OR :minCostPerLaunch IS NULL) AND (r.costPerLaunch < :maxCostPerLaunch OR :maxCostPerLaunch IS NULL)" +
            "AND (r.firstFlight >= COALESCE(:firstFlightMin, r.firstFlight)) AND (r.firstFlight <= COALESCE(:firstFlightMax, r.firstFlight))")
    List<RocketEntity> filteringSearch(Double minHeight, Double maxHeight, Double minDiameter, Double maxDiameter, Long minCostPerLaunch, Long maxCostPerLaunch, Integer minMass, Integer maxMass, LocalDate firstFlightMin, LocalDate firstFlightMax);

}
