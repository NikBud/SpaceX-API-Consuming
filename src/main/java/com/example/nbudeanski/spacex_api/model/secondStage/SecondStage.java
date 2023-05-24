package com.example.nbudeanski.spacex_api.model.secondStage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SecondStage {
    private Thrust thrust;
    private Payloads payloads;
    private boolean reusable;
    private int engines;
    @JsonProperty("fuel_amount_tons")
    private int fuelAmountTons;
    @JsonProperty("burn_time_sec")
    private int burnTimeSec;

    public Thrust getThrust() {
        return thrust;
    }

    public void setThrust(Thrust thrust) {
        this.thrust = thrust;
    }

    public Payloads getPayloads() {
        return payloads;
    }

    public void setPayloads(Payloads payloads) {
        this.payloads = payloads;
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
