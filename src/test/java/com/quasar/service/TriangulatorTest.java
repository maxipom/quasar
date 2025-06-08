package com.quasar.service;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import org.junit.jupiter.api.Test;

import com.quasar.exception.PositionUndeterminableException;
import com.quasar.model.Satellite;

public class TriangulatorTest {

    @Test
    public void testGetLocation() {

        Satellite sat1 = new Satellite("A", new Point(1, 1));
        Satellite sat2 = new Satellite("B", new Point(3, 3));
        Satellite sat3 = new Satellite("C", new Point(5, 1));

        Triangulator triangulatorService = new Triangulator(sat1, sat2, sat3);

        double[] distances = {2, 2, 2};
        Point location = triangulatorService.GetLocation(distances);

        assertEquals(3, location.x);
        assertEquals(1, location.y);
    }

    @Test
    public void testGetLocationThrowsWhenImpossible() {
        Satellite sat1 = new Satellite("Kenobi", new Point(-500, -200));
        Satellite sat2 = new Satellite("Skywalker", new Point(100, -100));
        Satellite sat3 = new Satellite("Sato", new Point(500, 100));

        Triangulator triangulatorService = new Triangulator(sat1, sat2, sat3);

        double[] distances = {100.0, 115.5, 142.7};

        assertThrowsExactly(
                PositionUndeterminableException.class,
                () -> triangulatorService.GetLocation(distances)
        );
    }
}
