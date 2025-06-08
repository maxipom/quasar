package com.quasar.model;

public class TopSecretRequest {

    public SateliteStatus[] satellites;

    public TopSecretRequest(SateliteStatus[] satellites) {
        this.satellites = satellites;
    }
}
