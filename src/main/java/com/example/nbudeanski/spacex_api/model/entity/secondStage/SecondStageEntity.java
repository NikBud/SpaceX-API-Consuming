package com.example.nbudeanski.spacex_api.model.entity.secondStage;

import com.example.nbudeanski.spacex_api.model.entity.RocketEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "second_stage")
public class SecondStageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "secondStage", cascade = CascadeType.ALL)
    private ThrustEntity thrust;

    @OneToOne(mappedBy = "secondStage", cascade = CascadeType.ALL)
    private PayloadsEntity payloads;

    @Column(name = "reusable")
    private boolean reusable;

    @Column(name = "engines")
    private int engines;

    @Column(name = "fuel_amount_tons")
    private int fuelAmountTons;

    @Column(name = "burn_time_sec")
    private int burnTimeSec;

    @OneToOne
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

    public ThrustEntity getThrust() {
        return thrust;
    }

    public void setThrust(ThrustEntity thrustEntity) {
        this.thrust = thrustEntity;
        thrustEntity.setSecondStage(this);
    }

    public PayloadsEntity getPayloads() {
        return payloads;
    }

    public void setPayloads(PayloadsEntity payloadsEntity) {
        this.payloads = payloadsEntity;
        payloadsEntity.setSecondStage(this);
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

    public int getFuelAmountTons() {
        return fuelAmountTons;
    }

    public void setFuelAmountTons(int fuelAmountTons) {
        this.fuelAmountTons = fuelAmountTons;
    }

    public int getBurnTimeSec() {
        return burnTimeSec;
    }

    public void setBurnTimeSec(int burnTimeSec) {
        this.burnTimeSec = burnTimeSec;
    }
}
