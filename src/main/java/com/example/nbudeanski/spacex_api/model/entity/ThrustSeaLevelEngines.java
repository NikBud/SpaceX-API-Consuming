package com.example.nbudeanski.spacex_api.model.entity;

import com.example.nbudeanski.spacex_api.model.entity.engines.EnginesEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "thrust_sea_level_engines")
public class ThrustSeaLevelEngines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "kN")
    private int kN;

    @Column(name = "lbf")
    private long lbf;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engines_id", referencedColumnName = "id")
    private EnginesEntity engines;

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

    public EnginesEntity getEngines() {
        return engines;
    }

    public void setEngines(EnginesEntity engines) {
        this.engines = engines;
    }
}
