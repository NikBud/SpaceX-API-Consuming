package com.example.nbudeanski.spacex_api.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mass")
public class MassEntity implements Comparable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "kg")
    private int kg;

    @Column(name = "lb")
    private int lb;

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

    @Override
    public int compareTo(Object o) {
        MassEntity mass = (MassEntity) o;
        return Integer.compare(this.kg, mass.kg);
    }
}
