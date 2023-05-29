package com.example.nbudeanski.spacex_api.model.api.secondStage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payloads {

    @JsonProperty("composite_fairing")
    private CompositeFairing compositeFairing;
    @JsonProperty("option_1")
    private String option1;

    public CompositeFairing getCompositeFairing() {
        return compositeFairing;
    }

    public void setCompositeFairing(CompositeFairing compositeFairing) {
        this.compositeFairing = compositeFairing;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

}
