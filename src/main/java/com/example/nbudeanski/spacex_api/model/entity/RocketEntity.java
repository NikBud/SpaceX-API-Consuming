package com.example.nbudeanski.spacex_api.model.entity;

import com.example.nbudeanski.spacex_api.model.entity.engines.EnginesEntity;
import com.example.nbudeanski.spacex_api.model.entity.firstStage.FirstStageEntity;
import com.example.nbudeanski.spacex_api.model.entity.secondStage.SecondStageEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "rocket")
public class RocketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "rocket", cascade = CascadeType.ALL)
    private HeightRocket height;

    @OneToOne(mappedBy = "rocket", cascade = CascadeType.ALL)
    private DiameterRocket diameter;

    @OneToOne(mappedBy = "rocket", cascade = CascadeType.ALL)
    private MassEntity mass;

    @OneToOne(mappedBy = "rocket", cascade = CascadeType.ALL)
    private FirstStageEntity firstStage;

    @OneToOne(mappedBy = "rocket", cascade = CascadeType.ALL)
    private SecondStageEntity secondStage;

    @OneToOne(mappedBy = "rocket", cascade = CascadeType.ALL)
    private EnginesEntity engines;

    @OneToOne(mappedBy = "rocket", cascade = CascadeType.ALL)
    private LandingLegsEntity landingLegs;

    @OneToMany(mappedBy = "rocket", cascade = CascadeType.PERSIST)
    private List<PayloadWeightEntity> payloadWeights;

    @OneToMany(mappedBy = "rocket", cascade = CascadeType.PERSIST)
    private List<FlickrImage> flickrImages;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "active")
    private boolean active;

    @Column(name = "stages")
    private int stages;

    @Column(name = "boosters")
    private int boosters;

    @Column(name = "cost_per_launch")
    private long costPerLaunch;

    @Column(name = "success_rate_pct")
    private int successRatePTS;

    @Column(name = "first_flight")
    private LocalDate firstFlight;

    @Column(name = "company")
    private String company;

    @Column(name = "country")
    private String country;

    @Column(name = "wikipedia")
    private String wikipedia;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "rocket_id")
    private String rocket_id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FlickrImage> getFlickrImages() {
        return flickrImages;
    }

    public void setFlickrImages(List<FlickrImage> flickrImages) {
        this.flickrImages = flickrImages;
        flickrImages.forEach(flickrImage -> flickrImage.setRocket(this));
    }

    public String getRocket_id() {
        return rocket_id;
    }

    public void setRocket_id(String rocket_id) {
        this.rocket_id = rocket_id;
    }

    public HeightRocket getHeight() {
        return height;
    }

    public void setHeight(HeightRocket heightRocket) {
        this.height = heightRocket;
        heightRocket.setRocket(this);
    }

    public DiameterRocket getDiameter() {
        return diameter;
    }

    public void setDiameter(DiameterRocket diameterRocket) {
        this.diameter = diameterRocket;
        diameterRocket.setRocket(this);
    }

    public MassEntity getMass() {
        return mass;
    }

    public void setMass(MassEntity massEntity) {
        this.mass = massEntity;
        massEntity.setRocket(this);
    }

    public FirstStageEntity getFirstStage() {
        return firstStage;
    }

    public void setFirstStage(FirstStageEntity firstStageEntity) {
        this.firstStage = firstStageEntity;
        firstStageEntity.setRocket(this);
    }

    public SecondStageEntity getSecondStage() {
        return secondStage;
    }

    public void setSecondStage(SecondStageEntity secondStageEntity) {
        this.secondStage = secondStageEntity;
        secondStageEntity.setRocket(this);
    }

    public EnginesEntity getEngines() {
        return engines;
    }

    public void setEngines(EnginesEntity engines) {
        this.engines = engines;
        engines.setRocket(this);
    }

    public LandingLegsEntity getLandingLegs() {
        return landingLegs;
    }

    public void setLandingLegs(LandingLegsEntity landingLegsEntity) {
        this.landingLegs = landingLegsEntity;
        landingLegsEntity.setRocket(this);
    }

    public List<PayloadWeightEntity> getPayloadWeights() {
        return payloadWeights;
    }

    public void setPayloadWeights(List<PayloadWeightEntity> payloadWeightEntities) {
        this.payloadWeights = payloadWeightEntities;
        payloadWeightEntities.forEach(payloadWeightEntity -> payloadWeightEntity.setRocket(this));
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


    @Override
    public String toString() {
        return "Rocket: " +
                "name= " + name +
                ", id= " + id ;
    }
}
