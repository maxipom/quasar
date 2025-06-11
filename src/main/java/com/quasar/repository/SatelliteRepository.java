package com.quasar.repository;

import com.quasar.model.SatelliteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SatelliteRepository extends MongoRepository<SatelliteEntity, String> {
}
