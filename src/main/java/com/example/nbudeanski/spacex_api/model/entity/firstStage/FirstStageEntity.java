package com.example.nbudeanski.spacex_api.model.entity.firstStage;

import com.example.nbudeanski.spacex_api.model.entity.RocketEntity;
import com.example.nbudeanski.spacex_api.model.entity.ThrustSeaLevelFirstStage;
import com.example.nbudeanski.spacex_api.model.entity.ThrustVacuumFirstStage;
import jakarta.persistence.*;

@Entity
@Table(name = "first_stage")
public class FirstStageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "firstStage")
    private ThrustSeaLevelFirstStage thrustSeaLevel;

    @OneToOne(mappedBy = "firstStage")
    private ThrustVacuumFirstStage thrustVacuum;

    @Column(name = "reusable")
    private boolean reusable;

    @Column(name = "engines")
    private int engines;

    @Column(name = "fuel_amount_tones")
    private int fuelAmountTones;

    @Column(name = "burn_time_sec")
    private int burnTimeSec;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rocket_id", referencedColumnName = "id")
    private RocketEntity rocket;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RocketEntity getRocket() {
        return rocket;
    }

    public void setRocket(RocketEntity rocket) {
        this.rocket = rocket;
    }

    public ThrustSeaLevelFirstStage getThrustSeaLevel() {
        return thrustSeaLevel;
    }

    public void setThrustSeaLevel(ThrustSeaLevelFirstStage thrustSeaLevelFirstStage) {
        this.thrustSeaLevel = thrustSeaLevelFirstStage;
    }

    public ThrustVacuumFirstStage getThrustVacuum() {
        return thrustVacuum;
    }

    public void setThrustVacuum(ThrustVacuumFirstStage thrustVacuumFirstStage) {
        this.thrustVacuum = thrustVacuumFirstStage;
    }

    public boolean isReusable() {
        return reusable;
    }

    public void setReusable(boolean reusable) {
        this.reusable = reusable;
    }

    public int getEngines() {
        return engines;
    }

    public void setEngines(int engines) {
        this.engines = engines;
    }

    public int getFuelAmountTones() {
        return fuelAmountTones;
    }

    public void setFuelAmountTones(int fuelAmountTones) {
        this.fuelAmountTones = fuelAmountTones;
    }

    public int getBurnTimeSec() {
        return burnTimeSec;
    }

    public void setBurnTimeSec(int burnTimeSec) {
        this.burnTimeSec = burnTimeSec;
    }
}
