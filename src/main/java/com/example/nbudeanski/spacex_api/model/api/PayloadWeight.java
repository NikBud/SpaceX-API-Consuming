package com.example.nbudeanski.spacex_api.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayloadWeight {

    @JsonProperty("id")
    private String PayloadWeightId;
    private String name;
    private int kg;
    private int lb;


    public String getPayloadWeightId() {
        return PayloadWeightId;
    }

    public void setPayloadWeightId(String payloadWeightId) {
        PayloadWeightId = payloadWeightId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }

    public int getLb() {
        return lb;
    }

    public void setLb(int lb) {
        this.lb = lb;
    }
}
