package com.quasar.controller;

import com.quasar.model.SatelliteEntity;
import com.quasar.model.SatelliteRequest;
import com.quasar.service.SatelliteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topsecret_split")
public class TopSecretSplit {
    private final SatelliteService satelliteService;

    public TopSecretSplit(SatelliteService satelliteService) {
        this.satelliteService = satelliteService;
    }
    @PostMapping("/{name}")
    public ResponseEntity<SatelliteEntity> UpdateSatellite(@PathVariable String name, @RequestBody SatelliteRequest satelliteRequest){
        SatelliteEntity satellite =  this.satelliteService.saveSatellite(
                name,
                satelliteRequest.distance,
                satelliteRequest.message
        );
        return ResponseEntity.ok(satellite);
    }

}
