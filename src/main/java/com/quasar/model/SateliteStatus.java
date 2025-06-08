package com.quasar.model;

public class SateliteStatus {

    public String name;
    public Float distance;
    public String[] message;

    public SateliteStatus(String name, Float distance, String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }
}
