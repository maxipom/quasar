package com.quasar.model;

public class SatelliteRequest {

    public double distance;
    public String[] message;

    public SatelliteRequest() {
    }

    public SatelliteRequest(double distance, String[] message) {
        this.distance = distance;
        this.message = message;
    }

}
