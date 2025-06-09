package com.quasar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "satellites")
public class SatelliteEntity {

    @Id
    private String name;
    private double distance;
    private String[] message;

    // Constructor vacío (requerido por Spring)
    public SatelliteEntity() {
    }

    public SatelliteEntity(String name, double distance, String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }
}
