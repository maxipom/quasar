package com.quasar.service;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import org.junit.jupiter.api.Test;

import com.quasar.exception.PositionUndeterminableException;
import com.quasar.model.Satelite;

public class TriangulatorTest {

    @Test
    public void testGetLocation() {

        Satelite sat1 = new Satelite("A", new Point(1, 1));
        Satelite sat2 = new Satelite("B", new Point(3, 3));
        Satelite sat3 = new Satelite("C", new Point(5, 1));

        Triangulator triangulatorService = new Triangulator(sat1, sat2, sat3);

        double[] distances = {2, 2, 2};
        Point location = triangulatorService.GetLocation(distances);

        assertEquals(3, location.x);
        assertEquals(1, location.y);
    }

    @Test
    public void testGetLocationThrowsWhenImpossible() {
        Satelite sat1 = new Satelite("Kenobi", new Point(-500, -200));
        Satelite sat2 = new Satelite("Skywalker", new Point(100, -100));
        Satelite sat3 = new Satelite("Sato", new Point(500, 100));

        Triangulator triangulatorService = new Triangulator(sat1, sat2, sat3);

        double[] distances = {100.0, 115.5, 142.7};

        assertThrowsExactly(
                PositionUndeterminableException.class,
                () -> triangulatorService.GetLocation(distances)
        );
    }
}
