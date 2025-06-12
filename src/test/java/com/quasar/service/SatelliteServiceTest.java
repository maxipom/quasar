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
        SatelliteEntity incoming = new SatelliteEntity(name, 100.0, new String[]{"este", "", "", "mensaje", ""}, null);

        when(satelliteRepository.findById(name)).thenReturn(Optional.empty());

        assertThrowsExactly(IllegalArgumentException.class,
                () -> satelliteService.findAndUpdateSatellite(name, incoming));
    }

    @Test
    public void testFindAndUpdateSatellite_UpdateExisting() {
        String name = "kenobi";
        SatelliteEntity existing = new SatelliteEntity(name, 50.0, new String[]{"", "", "", "", ""}, new Point(0, 0));
        SatelliteEntity incoming = new SatelliteEntity(name, 120.0, new String[]{"este", "", "", "mensaje", ""}, null);

        when(satelliteRepository.findById(name)).thenReturn(Optional.of(existing));
        when(satelliteRepository.save(any(SatelliteEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SatelliteEntity result = satelliteService.findAndUpdateSatellite(name, incoming);

        assertEquals(name, result.name);
        assertEquals(120.0, result.distance);
        assertArrayEquals(incoming.message, result.message);

        assertEquals(existing.position, result.position);
        verify(satelliteRepository).save(any(SatelliteEntity.class));
    }

    @Test
    public void testSaveOrUpdateSatellite_CreateNew() {
        String name = "skywalker";
        SatelliteEntity incoming = new SatelliteEntity(name, 115.5, new String[]{"", "es", "", "", "secreto"}, new Point(1, 1));

        when(satelliteRepository.findById(name)).thenReturn(Optional.empty());
        when(satelliteRepository.save(any(SatelliteEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SatelliteEntity result = satelliteService.saveOrUpdateSatellite(name, incoming);

        assertEquals(name.toLowerCase(), result.name);
        assertEquals(115.5, result.distance);
        assertArrayEquals(incoming.message, result.message);
        assertEquals(new Point(1, 1), result.position);
    }

    @Test
    public void testSaveOrUpdateSatellite_UpdateExisting() {
        String name = "sato";
        SatelliteEntity existing = new SatelliteEntity(name, 80.0, new String[]{"", "", "", "", ""}, new Point(2, 2));
        SatelliteEntity incoming = new SatelliteEntity(name, null, new String[]{"este", "", "un", "", ""}, null);

        when(satelliteRepository.findById(name)).thenReturn(Optional.of(existing));
        when(satelliteRepository.save(any(SatelliteEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SatelliteEntity result = satelliteService.saveOrUpdateSatellite(name, incoming);

        assertEquals(name, result.name);
        assertArrayEquals(incoming.message, result.message);

        assertEquals(80.0, result.distance);
        assertEquals(new Point(2, 2), result.position);
    }
}
