package com.quasar.model;

public class TopSecretRequest {

    public SatelliteStatus[] satellites;

    public TopSecretRequest(SatelliteStatus[] satellites) {
        this.satellites = satellites;
    }
}
