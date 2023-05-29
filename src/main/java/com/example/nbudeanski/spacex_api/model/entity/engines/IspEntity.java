package com.example.nbudeanski.spacex_api.model.entity.engines;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "isp")
public class IspEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sea_level")
    private int seaLevel;

    @Column(name = "vacuum")
    private int vacuum;

    @OneToOne
    @JoinColumn(name = "engines_id", referencedColumnName = "id")
    private EnginesEntity engines;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnginesEntity getEngines() {
        return engines;
    }

    public void setEngines(EnginesEntity engines) {
        this.engines = engines;
    }

    public int getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(int seaLevel) {
        this.seaLevel = seaLevel;
    }

    public int getVacuum() {
        return vacuum;
    }

    public void setVacuum(int vacuum) {
        this.vacuum = vacuum;
    }
}
