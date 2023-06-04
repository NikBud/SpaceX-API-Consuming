package com.example.nbudeanski.spacex_api.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payload_weight")
public class PayloadWeightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "json_id")
    private String PayloadWeightId;

    @Column(name = "name")
    private String name;

    @Column(name = "kg")
    private int kg;

    @Column(name = "lb")
    private int lb;

    @ManyToOne
    @JoinColumn(name = "rocket_id")
    private RocketEntity rocket;

    public PayloadWeightEntity() {

    }

    public PayloadWeightEntity(String payloadWeightId, String name, int kg, int lb) {
        PayloadWeightId = payloadWeightId;
        this.name = name;
        this.kg = kg;
        this.lb = lb;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayloadWeightId() {
        return PayloadWeightId;
    }

    public void setPayloadWeightId(String payloadWeightId) {
        PayloadWeightId = payloadWeightId;
    }

    public RocketEntity getRocket() {
        return rocket;
    }

    public void setRocket(RocketEntity rocket) {
        this.rocket = rocket;
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
