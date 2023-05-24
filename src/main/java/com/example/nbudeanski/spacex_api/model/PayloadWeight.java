package com.example.nbudeanski.spacex_api.model;

public class PayloadWeight {
    private String id;
    private String name;
    private int kg;
    private int lb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
