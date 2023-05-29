package com.example.nbudeanski.spacex_api.model.api.secondStage;

import com.example.nbudeanski.spacex_api.model.api.Diameter;
import com.example.nbudeanski.spacex_api.model.api.Height;

public class CompositeFairing {
    private Height height;
    private Diameter diameter;

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public Diameter getDiameter() {
        return diameter;
    }

    public void setDiameter(Diameter diameter) {
        this.diameter = diameter;
    }
}
