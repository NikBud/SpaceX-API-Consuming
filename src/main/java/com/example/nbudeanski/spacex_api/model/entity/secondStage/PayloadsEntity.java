package com.example.nbudeanski.spacex_api.model.entity.secondStage;

import jakarta.persistence.*;

@Entity
@Table(name = "payloads")
public class PayloadsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "payload")
    private CompositeFairingEntity compositeFairing;

    @Column(name = "option_1")
    private String option1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_stage_id", referencedColumnName = "id")
    private SecondStageEntity secondStage;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SecondStageEntity getSecondStage() {
        return secondStage;
    }

    public void setSecondStage(SecondStageEntity secondStage) {
        this.secondStage = secondStage;
    }

    public CompositeFairingEntity getCompositeFairing() {
        return compositeFairing;
    }

    public void setCompositeFairing(CompositeFairingEntity compositeFairingEntity) {
        this.compositeFairing = compositeFairingEntity;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

}
