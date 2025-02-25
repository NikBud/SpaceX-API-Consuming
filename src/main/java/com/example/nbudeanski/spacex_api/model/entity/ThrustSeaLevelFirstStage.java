package com.example.nbudeanski.spacex_api.model.entity;

import com.example.nbudeanski.spacex_api.model.entity.firstStage.FirstStageEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "thrust_sea_level_first_stage")
public class ThrustSeaLevelFirstStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "kN")
    private int kN;

    @Column(name = "lbf")
    private long lbf;

    @OneToOne
    @JoinColumn(name = "first_stage_id", referencedColumnName = "id")
    private FirstStageEntity firstStage;

    public FirstStageEntity getFirstStage() {
        return firstStage;
    }

    public void setFirstStage(FirstStageEntity firstStage) {
        this.firstStage = firstStage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
