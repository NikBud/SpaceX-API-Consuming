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

    @Query("SELECT r FROM RocketEntity r WHERE r.height.meters BETWEEN :h1 AND :h2")
    List<RocketEntity> findByHeightBetween(double h1, double h2);


    @Query("SELECT r FROM RocketEntity r WHERE r.diameter.meters BETWEEN :d1 AND :d2")
    List<RocketEntity> findByDiameterBetween(double d1, double d2);

    List<RocketEntity> findByCostPerLaunchBetween(long p1, long p2);

    @Query("SELECT r FROM RocketEntity r WHERE r.mass.kg BETWEEN :m1 AND :m2")
    List<RocketEntity> findByMassBetween(int m1, int m2);

    List<RocketEntity> findByFirstFlightBetween(LocalDate d1, LocalDate d2);

    @Query("SELECT r FROM RocketEntity r WHERE r.height.meters > :height")
    List<RocketEntity> findByHeightGreaterThan(double height);

    @Query("SELECT r FROM RocketEntity r WHERE r.height.meters < :height")
    List<RocketEntity> findByHeightLowerThan(double height);

    @Query("SELECT r FROM RocketEntity r WHERE r.diameter.meters > :diameter")
    List<RocketEntity> findByDiameterGreaterThan(double diameter);

    @Query("SELECT r FROM RocketEntity  r WHERE r.diameter.meters < :diameter")
    List<RocketEntity> findByDiameterLowerThan(double diameter);

    List<RocketEntity> findByCostPerLaunchGreaterThan(Long p);

    @Query("SELECT r FROM RocketEntity r WHERE r.costPerLaunch < :p")
    List<RocketEntity> findByCostPerLaunchLowerThan(Long p);

    @Query("SELECT r FROM RocketEntity r WHERE r.mass.kg > :mass")
    List<RocketEntity> findByMassGreaterThan(int mass);

    @Query("SELECT r FROM RocketEntity r WHERE r.mass.kg < :mass")
    List<RocketEntity> findByMassLowerThan(int mass);

    List<RocketEntity> findByFirstFlightAfter(LocalDate date);

    List<RocketEntity> findByFirstFlightBefore(LocalDate date);

    @Query("SELECT r FROM RocketEntity r ORDER BY r.height.meters")
    List<RocketEntity> findAllOrderedByHeightAsc();

    @Query("SELECT r FROM RocketEntity r ORDER BY r.height.meters DESC")
    List<RocketEntity> findAllOrderedByHeightDesc();

    @Query("SELECT r FROM RocketEntity r ORDER BY r.diameter.meters")
    List<RocketEntity> findAllOrderedByDiameterAsc();

    @Query("SELECT r FROM RocketEntity r ORDER BY r.diameter.meters DESC")
    List<RocketEntity> findAllOrderedByDiameterDesc();

    @Query("SELECT r FROM RocketEntity r ORDER BY r.mass.kg")
    List<RocketEntity> findAllOrderedByMassAsc();

    @Query("SELECT r FROM RocketEntity r ORDER BY r.mass.kg DESC")
    List<RocketEntity> findAllOrderedByMassDesc();

    @Query("SELECT r FROM RocketEntity r ORDER BY r.costPerLaunch")
    List<RocketEntity> findAllOrderedByCostPerLaunchAsc();

    @Query("SELECT r FROM RocketEntity r ORDER BY r.costPerLaunch DESC")
    List<RocketEntity> findAllOrderedByCostPerLaunchDesc();

    @Query("SELECT r FROM RocketEntity r ORDER BY r.firstFlight")
    List<RocketEntity> findAllOrderByFirstFlightAsc();

    @Query("SELECT r FROM RocketEntity r ORDER BY r.firstFlight DESC")
    List<RocketEntity> findAllOrderByFirstFlightDesc();


}
