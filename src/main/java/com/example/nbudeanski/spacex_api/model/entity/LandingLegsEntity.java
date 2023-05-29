package com.example.nbudeanski.spacex_api.model.entity;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Table(name = "landing_legs")
public class LandingLegsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private int number;

    @Column(name = "material")
    private String material;

    @OneToOne(cascade = CascadeType.ALL)
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
