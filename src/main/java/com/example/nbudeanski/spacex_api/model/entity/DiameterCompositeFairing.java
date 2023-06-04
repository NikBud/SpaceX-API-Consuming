package com.example.nbudeanski.spacex_api.model.entity;

import com.example.nbudeanski.spacex_api.model.entity.secondStage.CompositeFairingEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "diameter_composite_fairing")
public class DiameterCompositeFairing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "meters")
    private double meters;

    @Column(name = "feet")
    private double feet;

    @OneToOne
    @JoinColumn(name = "composite_fairing_id", referencedColumnName = "id")
    private CompositeFairingEntity compositeFairing;


    public CompositeFairingEntity getCompositeFairing() {
        return compositeFairing;
    }

    public void setCompositeFairing(CompositeFairingEntity compositeFairing) {
        this.compositeFairing = compositeFairing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
