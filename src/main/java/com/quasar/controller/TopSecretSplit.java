package com.quasar.controller;

import com.quasar.model.SatelliteEntity;
import com.quasar.model.SatelliteRequest;
import com.quasar.model.Transmission;
import com.quasar.service.SatelliteService;
import com.quasar.service.TransmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/topsecret_split")
public class TopSecretSplit {
    private final SatelliteService satelliteService;
    private final TransmissionService transmissionService;

    public TopSecretSplit(SatelliteService satelliteService, TransmissionService transmissionService) {
        this.satelliteService = satelliteService;
        this.transmissionService = transmissionService;
    }

    @PostMapping("/{name}")
    public ResponseEntity<SatelliteEntity> UpdateSatellite(@PathVariable String name, @RequestBody SatelliteRequest satelliteRequest) {
        SatelliteEntity satellite = this.satelliteService.saveOrUpdateSatellite(
                name,
                satelliteRequest.distance,
                satelliteRequest.message,
                satelliteRequest.position
        );
        return ResponseEntity.ok(satellite);
    }

    @GetMapping("/")
    public ResponseEntity<Transmission> GetTriangulation() {
        try {
            SatelliteEntity kenobi = this.satelliteService.getSatellite("kenobi");
            SatelliteEntity skywalker = this.satelliteService.getSatellite("skywalker");
            SatelliteEntity sato = this.satelliteService.getSatellite("sato");
            if (kenobi == null || skywalker == null || sato == null) {
                return ResponseEntity.notFound().build();
            }

            Transmission newTransmission = this.transmissionService.calculateTransmission(kenobi, skywalker, sato);
            return ResponseEntity.ok(newTransmission);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Not enough information to determine position or message.");
        }
    }

}
