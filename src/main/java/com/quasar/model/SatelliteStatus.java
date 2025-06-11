package com.quasar.model;

public class SatelliteStatus {

    public String name;
    public Double distance;
    public String[] message;

    public SatelliteStatus(String name, Double distance, String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }
}
