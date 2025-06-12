// src/test/java/com/quasar/service/SatelliteServiceTest.java
package com.quasar.service;

import com.quasar.model.SatelliteEntity;
import com.quasar.repository.SatelliteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SatelliteServiceTest {

    private SatelliteRepository satelliteRepository;
    private SatelliteService satelliteService;

    @BeforeEach
    public void setUp() {
        satelliteRepository = mock(SatelliteRepository.class);
        satelliteService = new SatelliteService(satelliteRepository);
    }

    @Test
    public void testFindAndUpdateSatelliteErrorOnNew() {
        String name = "kenobi";
        Double distance = 100.0;
        String[] message = {"este", "", "", "mensaje", ""};

        when(satelliteRepository.findById(name)).thenReturn(Optional.empty());
        when(satelliteRepository.save(any(SatelliteEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        assertThrowsExactly(IllegalArgumentException.class,
                () -> satelliteService.findAndUpdateSatellite(name, distance, message));

    }

    @Test
    public void testFindAndUpdateSatellite_UpdateExisting() {
        String name = "kenobi";
        Double oldDistance = 50.0;
        Double newDistance = 120.0;
        String[] oldMessage = {"", "", "", "", ""};
        String[] newMessage = {"este", "", "", "mensaje", ""};
        SatelliteEntity existing = new SatelliteEntity(name, oldDistance, oldMessage, new Point(0, 0));

        when(satelliteRepository.findById(name)).thenReturn(Optional.of(existing));
        when(satelliteRepository.save(any(SatelliteEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SatelliteEntity result = satelliteService.findAndUpdateSatellite(name, newDistance, newMessage);

        assertEquals(name, result.name);
        assertEquals(newDistance, result.distance);
        assertArrayEquals(newMessage, result.message);
        verify(satelliteRepository).save(any(SatelliteEntity.class));
    }
}