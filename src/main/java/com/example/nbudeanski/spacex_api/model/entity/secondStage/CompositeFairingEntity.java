package com.example.nbudeanski.spacex_api.model.entity.secondStage;

import com.example.nbudeanski.spacex_api.model.entity.DiameterCompositeFairing;
import com.example.nbudeanski.spacex_api.model.entity.DiameterRocket;
import com.example.nbudeanski.spacex_api.model.entity.HeightCompositeFairing;
import com.example.nbudeanski.spacex_api.model.entity.HeightRocket;
import jakarta.persistence.*;

@Entity
@Table(name = "composite_fairing")
public class CompositeFairingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "compositeFairing")
    private HeightCompositeFairing height;

    @OneToOne(mappedBy = "compositeFairing")
    private DiameterCompositeFairing diameter;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payload_id", referencedColumnName = "id")
    private PayloadsEntity payload;


    public PayloadsEntity getPayload() {
        return payload;
    }

    public void setPayload(PayloadsEntity payload) {
        this.payload = payload;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PayloadsEntity getPayloads() {
        return payload;
    }

    public void setPayloads(PayloadsEntity payloads) {
        this.payload = payloads;
    }

    public HeightCompositeFairing getHeight() {
        return height;
    }

    public void setHeight(HeightCompositeFairing height) {
        this.height = height;
    }

    public DiameterCompositeFairing getDiameter() {
        return diameter;
    }

    public void setDiameter(DiameterCompositeFairing diameter) {
        this.diameter = diameter;
    }


}
