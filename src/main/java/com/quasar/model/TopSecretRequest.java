package com.quasar.model;

public class TopSecretRequest {

    public SatelliteEntity[] satellites;

    public TopSecretRequest() {
    }

    public TopSecretRequest(SatelliteEntity[] satellites) {
        this.satellites = satellites;
    }
}
