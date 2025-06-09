package com.quasar.service;

import com.quasar.model.Satellite;
import org.springframework.stereotype.Service;

import com.quasar.model.SatelliteEntity;
import com.quasar.repository.SatelliteRepository;

@Service
public class SatelliteService {

    private final SatelliteRepository satelliteRepository;

    public SatelliteService(SatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }

    public SatelliteEntity saveSatellite(String name, double distance, String[] message) {
       return satelliteRepository.save(new SatelliteEntity(name.toLowerCase(), distance, message));
    }

}
