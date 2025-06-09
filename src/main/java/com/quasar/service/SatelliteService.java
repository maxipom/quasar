package com.quasar.service;

import com.quasar.model.Satellite;
import org.springframework.stereotype.Service;

import com.quasar.model.SatelliteEntity;
import com.quasar.repository.SatelliteRepository;

import java.awt.*;

@Service
public class SatelliteService {

    private final SatelliteRepository satelliteRepository;

    public SatelliteService(SatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }

    public SatelliteEntity saveOrUpdateSatellite(String name, Double distance, String[] message, Point position) {
        SatelliteEntity entity = satelliteRepository.findById(name.toLowerCase()).orElse(null);

        if (entity == null) {
            // Create new entity
            return satelliteRepository.save(new SatelliteEntity(name.toLowerCase(), distance, message, position));
        } else {
            // Update only provided fields, keep others as is
            double newDistance = distance != null ? distance : entity.distance;
            String[] newMessage = message != null ? message : entity.message;
            Point newPosition = position != null ? position : entity.position;

            return satelliteRepository.save(new SatelliteEntity(entity.name, newDistance, newMessage, newPosition));
        }
    }

    public SatelliteEntity getSatellite(String name) {
        return satelliteRepository.findById(name.toLowerCase()).orElse(null);
    }

}
