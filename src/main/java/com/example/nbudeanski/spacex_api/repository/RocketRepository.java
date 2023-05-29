package com.example.nbudeanski.spacex_api.repository;

import com.example.nbudeanski.spacex_api.model.entity.RocketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RocketRepository extends JpaRepository<RocketEntity, Long> {
    Optional<RocketEntity> findById(Long id);
}
