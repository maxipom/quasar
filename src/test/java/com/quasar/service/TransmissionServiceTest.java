package com.quasar.service;

import com.quasar.model.SatelliteEntity;
import com.quasar.model.Transmission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.*;

public class TransmissionServiceTest {

    private TransmissionService transmissionService;

    @BeforeEach
    public void setUp() {
        transmissionService = new TransmissionService();
    }

    @Test
    void testDecodeTransmission_success() {
        SatelliteEntity sat1 = new SatelliteEntity("A",2.0, new String[]{"este", "", "", "mensaje", ""}, new Point(1, 1));
        SatelliteEntity sat2 = new SatelliteEntity("B",2.0,new String[]{"", "es", "", "", "secreto"}, new Point(3, 3));
        SatelliteEntity sat3 = new SatelliteEntity("C",2.0,new String[]{"este", "", "un", "", ""}, new Point(5, 1));

        Transmission result = transmissionService.calculateTransmission(sat1,sat2,sat3);

        assertEquals("este es un mensaje secreto", result.message);
        assertNotNull(result.position);
    }
}