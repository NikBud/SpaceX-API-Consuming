package com.example.nbudeanski.spacex_api.repository;

import com.example.nbudeanski.spacex_api.model.entity.RocketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RocketRepository extends JpaRepository<RocketEntity, Long> {

    @Query("SELECT r FROM RocketEntity r WHERE r.rocket_id = :id")
    Optional<RocketEntity> findByRocketId(String id);

    Optional<RocketEntity> findByNameIgnoreCase(String name);

}
