package com.example.nbudeanski.spacex_api.model.api;

import com.example.nbudeanski.spacex_api.model.api.engines.Engines;
import com.example.nbudeanski.spacex_api.model.api.firstStage.FirstStage;
import com.example.nbudeanski.spacex_api.model.api.secondStage.SecondStage;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.List;

public class Rocket {

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
    private int stages;
    private int boosters;
    @JsonProperty("cost_per_launch")
    private long costPerLaunch;
    @JsonProperty("success_rate_pct")
    private int successRatePTS;
    @JsonProperty("first_flight")
    private LocalDate firstFlight;
    private String company;
    private String country;

    private String wikipedia;
    private String description;
    @JsonProperty("id")
    private String rocket_id;




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

    public int getStages() {
        return stages;
    }

    public void setStages(int stages) {
        this.stages = stages;
    }

    public int getBoosters() {
        return boosters;
    }

    public void setBoosters(int boosters) {
        this.boosters = boosters;
    }

    public long getCostPerLaunch() {
        return costPerLaunch;
    }

    public void setCostPerLaunch(long costPerLaunch) {
        this.costPerLaunch = costPerLaunch;
    }

    public int getSuccessRatePTS() {
        return successRatePTS;
    }

    public void setSuccessRatePTS(int successRatePTS) {
        this.successRatePTS = successRatePTS;
    }

    public LocalDate getFirstFlight() {
        return firstFlight;
    }

    public void setFirstFlight(LocalDate firstFlight) {
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

    public String getRocket_id() {
        return rocket_id;
    }

    public void setRocket_id(String rocket_id) {
        this.rocket_id = rocket_id;
    }

    @Override
    public String toString() {
        return "Rocket: " +
                "name= " + name +
                ", id= " + rocket_id ;
    }
}
