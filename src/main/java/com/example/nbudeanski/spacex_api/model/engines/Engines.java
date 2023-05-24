package com.example.nbudeanski.spacex_api.model.engines;

import com.example.nbudeanski.spacex_api.model.ThrustVacuum;
import com.example.nbudeanski.spacex_api.model.ThrustSeaLevel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Engines {
    private Isp isp;
    @JsonProperty("thrust_sea_level")
    private ThrustSeaLevel thrustSeaLevel;
    @JsonProperty("thrust_vacuum")
    private ThrustVacuum thrustVacuum;
    private int number;
    private String type;
    private String layout;
    @JsonProperty("engine_loss_max")
    private int engineLossMax;

    @JsonProperty("propellant_1")
    private String propellant1;
    @JsonProperty("propellant_2")
    private String propellant2;
    @JsonProperty("thrust_to_weight")
    private double thrustToWeight;

    public Isp getIsp() {
        return isp;
    }

    public void setIsp(Isp isp) {
        this.isp = isp;
    }

    public ThrustSeaLevel getThrustSeaLevel() {
        return thrustSeaLevel;
    }

    public void setThrustSeaLevel(ThrustSeaLevel thrustSeaLevel) {
        this.thrustSeaLevel = thrustSeaLevel;
    }

    public ThrustVacuum getThrustVacuum() {
        return thrustVacuum;
    }

    public void setThrustVacuum(ThrustVacuum thrustVacuum) {
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
