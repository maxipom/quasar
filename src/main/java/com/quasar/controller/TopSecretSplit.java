package com.quasar.controller;

import com.quasar.model.SatelliteEntity;
import com.quasar.model.SatelliteRequest;
import com.quasar.model.TopSecretResponse;
import com.quasar.service.SatelliteService;
import com.quasar.service.Triangulator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/topsecret_split")
public class TopSecretSplit {
    private final SatelliteService satelliteService;

    public TopSecretSplit(SatelliteService satelliteService) {
        this.satelliteService = satelliteService;
    }
    @PostMapping("/{name}")
    public ResponseEntity<SatelliteEntity> UpdateSatellite(@PathVariable String name, @RequestBody SatelliteRequest satelliteRequest){
        SatelliteEntity satellite =  this.satelliteService.saveOrUpdateSatellite(
                name,
                satelliteRequest.distance,
                satelliteRequest.message,
                satelliteRequest.position
        );
        return ResponseEntity.ok(satellite);
    }

    @GetMapping("/")
    public ResponseEntity<TopSecretResponse> GetTriangulation() {
        SatelliteEntity kenobi = this.satelliteService.getSatellite("kenobi");
        SatelliteEntity skywalker = this.satelliteService.getSatellite("skywalker");
        SatelliteEntity sato = this.satelliteService.getSatellite("sato");
        if(kenobi == null|| skywalker == null || sato == null) {
            return ResponseEntity.notFound().build();
        }
        Triangulator triangulator = new Triangulator(kenobi,skywalker,sato);
        double[] distances = new double[]{
                kenobi.distance,
                skywalker.distance,
                sato.distance
        };
        Point position = triangulator.getLocation(distances);
        TopSecretResponse newTopSecretResponse = new TopSecretResponse(
                position,
                "Test"
        );
        return ResponseEntity.ok(newTopSecretResponse);
    }

}
