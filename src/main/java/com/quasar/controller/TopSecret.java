package com.quasar.controller;

import java.awt.Point;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quasar.model.SateliteStatus;
import com.quasar.model.Satellite;
import com.quasar.model.TopSecretRequest;
import com.quasar.model.TopSecretResponse;
import com.quasar.service.MessageBuilder;
import com.quasar.service.SatelliteService;
import com.quasar.service.Triangulator;

@RestController
@RequestMapping("/topsecret")
public class TopSecret {

    @PostMapping("/")
    public ResponseEntity<TopSecretResponse> DecodeEnemyInformation(@RequestBody TopSecretRequest request) {
        SateliteStatus[] statuses = request.satellites;
        Satellite sat1 = new Satellite("Kenobi", new Point(-500, -200));
        Satellite sat2 = new Satellite("Skywalker", new Point(100, -100));
        Satellite sat3 = new Satellite("Sato", new Point(500, 100));

        Triangulator triangulator = new Triangulator(sat1, sat2, sat3);
        double[] distances = Arrays.stream(statuses)
                .filter(status -> status.distance > 0)
                .mapToDouble(status -> status.distance)
                .toArray();
        String[][] messages = Arrays.stream(statuses)
                .map(status -> status.message)
                .toArray(String[][]::new);

        MessageBuilder messageBuilder = new MessageBuilder();
        String message = messageBuilder.GetMessage(messages);
        Point distance = triangulator.GetLocation(distances);

        TopSecretResponse response = new TopSecretResponse(
                distance,
                message);
        return ResponseEntity.ok(response);
    }


}

