package com.quasar.model;

import java.awt.*;

public class SatelliteRequest {

    public Double distance;
    public String[] message;
    public Point position;

    public SatelliteRequest() {
    }

    public SatelliteRequest(Double distance, String[] message, Point position) {
        this.distance = distance;
        this.message = message;
        this.position = position;
    }

}
