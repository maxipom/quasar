package com.quasar.service;

import com.quasar.exception.PositionUndeterminableException;
import com.quasar.model.SatelliteEntity;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class TriangulatorTest {

    @Test
    public void testGetLocation() {

        SatelliteEntity sat1 = new SatelliteEntity("A", null, null, new Point(1, 1));
        SatelliteEntity sat2 = new SatelliteEntity("B", null, null, new Point(3, 3));
        SatelliteEntity sat3 = new SatelliteEntity("C", null, null, new Point(5, 1));

        Triangulator triangulatorService = new Triangulator(sat1, sat2, sat3);

        double[] distances = {2, 2, 2};
        Point location = triangulatorService.getLocation(distances);

        assertEquals(3, location.x);
        assertEquals(1, location.y);
    }

    @Test
    public void testGetLocationThrowsWhenImpossible() {
        SatelliteEntity sat1 = new SatelliteEntity("A", null, new String[]{"este", "", "", "mensaje", ""}, new Point(1, 1));
        SatelliteEntity sat2 = new SatelliteEntity("B", null, new String[]{"", "es", "", "", "secreto"}, new Point(3, 3));
        SatelliteEntity sat3 = new SatelliteEntity("C", null, new String[]{"este", "", "un", "", ""}, new Point(5, 1));

        Triangulator triangulatorService = new Triangulator(sat1, sat2, sat3);

        double[] distances = {100.0, 115.5, 142.7};

        assertThrowsExactly(
                PositionUndeterminableException.class,
                () -> triangulatorService.getLocation(distances)
        );
    }
}
