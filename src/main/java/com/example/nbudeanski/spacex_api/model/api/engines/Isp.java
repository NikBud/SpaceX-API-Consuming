package com.example.nbudeanski.spacex_api.model.api.engines;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Isp {

    @JsonProperty("sea_level")
    private int seaLevel;
    private int vacuum;

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
