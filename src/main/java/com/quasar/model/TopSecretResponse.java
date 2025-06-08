package com.quasar.model;

import java.awt.Point;

public class TopSecretResponse {

    public Point position;
    public String message;

    public TopSecretResponse(Point position, String message) {
        this.position = position;
        this.message = message;
    }
}
