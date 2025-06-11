package com.quasar.controller;

import com.quasar.model.SatelliteEntity;
import com.quasar.model.SatelliteStatus;
import com.quasar.model.TopSecretRequest;
import com.quasar.model.Transmission;
import com.quasar.service.SatelliteService;
import com.quasar.service.TransmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/topsecret")
public class TopSecret {
    private final SatelliteService satelliteService;
    private final TransmissionService transmissionService;

    public TopSecret(SatelliteService satelliteService, TransmissionService transmissionService) {
        this.satelliteService = satelliteService;
        this.transmissionService = transmissionService;
    }

    @PostMapping("/")
    public ResponseEntity<Transmission> DecodeEnemyInformation(@RequestBody TopSecretRequest request) {
        try {


            SatelliteStatus[] statuses = request.satellites;
            if (statuses.length != 3) {
                return ResponseEntity.badRequest().body(null);
            }
            List<SatelliteEntity> satellites = new ArrayList<>();
            for (SatelliteStatus status : statuses) {
                SatelliteEntity satelliteFromDB = this.satelliteService.findAndUpdateSatellite(status.name, status.distance, status.message);
                satellites.add(satelliteFromDB);
            }
            Transmission response = this.transmissionService.calculateTransmission(satellites.get(0), satellites.get(1), satellites.get(2));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not enough information to determine position or message.");
        }
    }
}

