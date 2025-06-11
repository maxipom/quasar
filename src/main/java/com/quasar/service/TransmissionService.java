package com.quasar.service;

import com.quasar.model.SatelliteEntity;
import com.quasar.model.Transmission;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class TransmissionService {
    public Transmission calculateTransmission(SatelliteEntity satellite1,
                                                 SatelliteEntity satellite2,
                                                 SatelliteEntity satellite3) {
        Triangulator triangulator = new Triangulator(satellite1,satellite2,satellite3);
        double[] distances = new double[]{
                satellite1.distance,
                satellite2.distance,
                satellite3.distance
        };
        Point position = triangulator.getLocation(distances);
        String[][] messages = new String[][]{
                satellite1.message,
                satellite2.message,
                satellite3.message
        };
        String message = new MessageBuilder().getMessage(messages);
        Transmission newTransmission = new Transmission(
                position,
                message
        );
        return newTransmission;
    }
}
