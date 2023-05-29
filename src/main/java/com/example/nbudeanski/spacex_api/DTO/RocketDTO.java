package com.example.nbudeanski.spacex_api.DTO;

import com.example.nbudeanski.spacex_api.model.api.*;
import com.example.nbudeanski.spacex_api.model.api.engines.Engines;
import com.example.nbudeanski.spacex_api.model.api.firstStage.FirstStage;
import com.example.nbudeanski.spacex_api.model.api.secondStage.SecondStage;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

public class RocketDTO {
    private Height height;
    private Diameter diameter;
    private Mass mass;

    @JsonProperty("first_stage")
    private FirstStage firstStage;
    @JsonProperty("second_stage")
    private SecondStage secondStage;

    private Engines engines;

    @JsonProperty("landing_legs")
    private LandingLegs landingLegs;
    @JsonProperty("payload_weights")
    private List<PayloadWeight> payloadWeights;

    @JsonProperty("flickr_images")
    private List<String> flickrImages;

    private String name;
    private String type;
    private boolean active;
    private Integer stages;
    private Integer boosters;
    @JsonProperty("cost_per_launch")
    private Integer costPerLaunch;
    @JsonProperty("success_rate_pct")
    private Integer successRatePTS;
    @JsonProperty("first_flight")
    private Timestamp firstFlight;
    private String company;
    private String country;

    private String wikipedia;
    private String description;
    private String id;

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public Diameter getDiameter() {
        return diameter;
    }

    public void setDiameter(Diameter diameter) {
        this.diameter = diameter;
    }

    public Mass getMass() {
        return mass;
    }

    public void setMass(Mass mass) {
        this.mass = mass;
    }

    public FirstStage getFirstStage() {
        return firstStage;
    }

    public void setFirstStage(FirstStage firstStage) {
        this.firstStage = firstStage;
    }

    public SecondStage getSecondStage() {
        return secondStage;
    }

    public void setSecondStage(SecondStage secondStage) {
        this.secondStage = secondStage;
    }

    public Engines getEngines() {
        return engines;
    }

    public void setEngines(Engines engines) {
        this.engines = engines;
    }

    public LandingLegs getLandingLegs() {
        return landingLegs;
    }

    public void setLandingLegs(LandingLegs landingLegs) {
        this.landingLegs = landingLegs;
    }

    public List<PayloadWeight> getPayloadWeights() {
        return payloadWeights;
    }

    public void setPayloadWeights(List<PayloadWeight> payloadWeights) {
        this.payloadWeights = payloadWeights;
    }

    public List<String> getFlickrImages() {
        return flickrImages;
    }

    public void setFlickrImages(List<String> flickrImages) {
        this.flickrImages = flickrImages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getStages() {
        return stages;
    }

    public void setStages(Integer stages) {
        this.stages = stages;
    }

    public Integer getBoosters() {
        return boosters;
    }

    public void setBoosters(Integer boosters) {
        this.boosters = boosters;
    }

    public Integer getCostPerLaunch() {
        return costPerLaunch;
    }

    public void setCostPerLaunch(Integer costPerLaunch) {
        this.costPerLaunch = costPerLaunch;
    }

    public Integer getSuccessRatePTS() {
        return successRatePTS;
    }

    public void setSuccessRatePTS(Integer successRatePTS) {
        this.successRatePTS = successRatePTS;
    }

    public Timestamp getFirstFlight() {
        return firstFlight;
    }

    public void setFirstFlight(Timestamp firstFlight) {
        this.firstFlight = firstFlight;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
