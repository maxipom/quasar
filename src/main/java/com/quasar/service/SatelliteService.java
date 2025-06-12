package com.quasar.service;

import com.quasar.model.SatelliteEntity;
import com.quasar.repository.SatelliteRepository;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class SatelliteService {

    private final SatelliteRepository satelliteRepository;

    public SatelliteService(SatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }

    public SatelliteEntity findAndUpdateSatellite(String name, SatelliteEntity satellite) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Satellite name cannot be null or empty.");
        }
        SatelliteEntity entity = satelliteRepository.findById(name.toLowerCase()).orElse(null);
        if (entity == null) {
            throw new IllegalArgumentException("Satellite with name " + name + " does not exist.");
        } else {
            return updateExistingSatellite(entity, satellite);
        }
    }

    public SatelliteEntity saveOrUpdateSatellite(String name, SatelliteEntity satellite) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Satellite name cannot be null or empty.");
        }
        SatelliteEntity entity = satelliteRepository.findById(name.toLowerCase()).orElse(null);
        if (entity == null) {
            Double distance = satellite.distance;
            String[] message = satellite.message;
            Point position = satellite.position;
            return satelliteRepository.save(new SatelliteEntity(name.toLowerCase(), distance, message, position));
        } else {
            return updateExistingSatellite(entity, satellite);
        }
    }

    public SatelliteEntity updateExistingSatellite(SatelliteEntity existing, SatelliteEntity satellite) {
        if (existing == null) {
            throw new IllegalArgumentException("Satellite does not exist.");
        }
        Double distance = satellite.distance != null ? satellite.distance : existing.distance;
        String[] message = satellite.message != null ? satellite.message : existing.message;
        Point position = satellite.position != null ? satellite.position : existing.position;

        return satelliteRepository.save(new SatelliteEntity(existing.name, distance, message, position));
    }

    public SatelliteEntity getSatellite(String name) {
        return satelliteRepository.findById(name.toLowerCase()).orElse(null);
    }

}
