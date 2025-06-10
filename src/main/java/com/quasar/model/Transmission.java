package com.quasar.model;

import java.awt.Point;

public class Transmission {

    public Point position;
    public String message;

    public Transmission(Point position, String message) {
        this.position = position;
        this.message = message;
    }
}
