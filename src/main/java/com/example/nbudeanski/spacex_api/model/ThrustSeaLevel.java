package com.example.nbudeanski.spacex_api.model;

public class ThrustSeaLevel {
    private int kN;
    private long lbf;

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
