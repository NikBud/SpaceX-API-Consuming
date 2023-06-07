package com.example.nbudeanski.spacex_api.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "diameter_rocket")
public class DiameterRocket implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "meters")
    private double meters;

    @Column(name = "feet")
    private double feet;

    @OneToOne
    @JoinColumn(name = "rocket_id", referencedColumnName = "id")
    private RocketEntity rocket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RocketEntity getRocket() {
        return rocket;
    }

    public void setRocket(RocketEntity rocket) {
        this.rocket = rocket;
    }

    public double getMeters() {
        return meters;
    }

    public void setMeters(double meters) {
        this.meters = meters;
    }

    public double getFeet() {
        return feet;
    }

    public void setFeet(double feet) {
        this.feet = feet;
    }

    @Override
    public int compareTo(Object o) {
        DiameterRocket diameter = (DiameterRocket) o;
        return Double.compare(this.meters, diameter.meters);
    }
}
