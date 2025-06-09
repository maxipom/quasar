package com.quasar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;

@Document(collection = "satellites")
public class SatelliteEntity {

    @Id
    public String name;
    public double distance;
    public String[] message;
    public Point position;

    public SatelliteEntity() {
    }

    public SatelliteEntity(String name, double distance, String[] message, Point position) {
        this.name = name;
        this.distance = distance;
        this.message = message;
        this.position = position;
    }

}
