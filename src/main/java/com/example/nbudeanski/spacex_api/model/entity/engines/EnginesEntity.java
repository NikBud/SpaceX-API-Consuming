package com.example.nbudeanski.spacex_api.model.entity.engines;

import com.example.nbudeanski.spacex_api.model.entity.*;
import jakarta.persistence.*;

@Entity
@Table(name = "engines")
public class EnginesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "engines")
    private IspEntity isp;

    @OneToOne(mappedBy = "engines")
    private ThrustSeaLevelEngines thrustSeaLevel;

    @OneToOne(mappedBy = "engines")
    private ThrustVacuumEngines thrustVacuum;

    @Column(name = "number")
    private int number;

    @Column(name = "type")
    private String type;

    @Column(name = "layout")
    private String layout;

    @Column(name = "engine_loss_max")
    private int engineLossMax;

    @Column(name = "propellant_1")
    private String propellant1;

    @Column(name = "propellant_2")
    private String propellant2;

    @Column(name = "thrust_to_weight")
    private double thrustToWeight;

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

    public IspEntity getIsp() {
        return isp;
    }

    public void setIsp(IspEntity ispEntity) {
        this.isp = ispEntity;
    }

    public ThrustSeaLevelEngines getThrustSeaLevel() {
        return thrustSeaLevel;
    }

    public void setThrustSeaLevel(ThrustSeaLevelEngines thrustSeaLevel) {
        this.thrustSeaLevel = thrustSeaLevel;
    }

    public ThrustVacuumEngines getThrustVacuum() {
        return thrustVacuum;
    }

    public void setThrustVacuum(ThrustVacuumEngines thrustVacuum) {
        this.thrustVacuum = thrustVacuum;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public int getEngineLossMax() {
        return engineLossMax;
    }

    public void setEngineLossMax(int engineLossMax) {
        this.engineLossMax = engineLossMax;
    }

    public String getPropellant1() {
        return propellant1;
    }

    public void setPropellant1(String propellant1) {
        this.propellant1 = propellant1;
    }

    public String getPropellant2() {
        return propellant2;
    }

    public void setPropellant2(String propellant2) {
        this.propellant2 = propellant2;
    }

    public double getThrustToWeight() {
        return thrustToWeight;
    }

    public void setThrustToWeight(double thrustToWeight) {
        this.thrustToWeight = thrustToWeight;
    }
}
