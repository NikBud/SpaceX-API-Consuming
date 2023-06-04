package com.example.nbudeanski.spacex_api.model.entity.secondStage;

import jakarta.persistence.*;

@Entity
@Table(name = "thrust")
public class ThrustEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "kN")
    private int kN;

    @Column(name = "lbf")
    private long lbf;

    @OneToOne
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

    public int getkN() {
        return kN;
    }

    public void setkN(int kN) {
        this.kN = kN;
    }

    public long getLbf() {
        return lbf;
    }

    public void setLbf(long lbf) {
        this.lbf = lbf;
    }
}
