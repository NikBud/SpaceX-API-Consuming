package com.example.nbudeanski.spacex_api.model.api.firstStage;

import com.example.nbudeanski.spacex_api.model.api.ThrustVacuum;
import com.example.nbudeanski.spacex_api.model.api.ThrustSeaLevel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FirstStage {
    @JsonProperty("thrust_sea_level")
    private ThrustSeaLevel thrustSeaLevel;
    @JsonProperty("thrust_vacuum")
    private ThrustVacuum thrustVacuum;
    private boolean reusable;
    private int engines;
    @JsonProperty("fuel_amount_tons")
    private int fuelAmountTones;
    @JsonProperty("burn_time_sec")
    private int burnTimeSec;

    public ThrustSeaLevel getThrustSeaLevel() {
        return thrustSeaLevel;
    }

    public void setThrustSeaLevel(ThrustSeaLevel thrustSeaLevel) {
        this.thrustSeaLevel = thrustSeaLevel;
    }

    public ThrustVacuum getThrusVacuum() {
        return thrustVacuum;
    }

    public void setThrusVacuum(ThrustVacuum thrustVacuum) {
        this.thrustVacuum = thrustVacuum;
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
