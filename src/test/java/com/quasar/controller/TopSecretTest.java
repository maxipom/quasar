package com.quasar.controller;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.quasar.model.SatelliteStatus;
import com.quasar.model.TopSecretRequest;
import com.quasar.model.TopSecretResponse;

public class TopSecretTest {

    @Test
    public void testResponseOk() {
        TopSecret controller = new TopSecret();
        SatelliteStatus[] statuses = new SatelliteStatus[]{
            new SatelliteStatus("kenobi", 538.52f, new String[]{"este", "", "", "mensaje", ""}),
            new SatelliteStatus("skywalker", 141.42f, new String[]{"", "es", "", "", "secreto"}),
            new SatelliteStatus("sato", 509.90f, new String[]{"este", "", "un", "", ""})
        };
        TopSecretRequest payload = new TopSecretRequest(statuses);
        var response = controller.DecodeEnemyInformation(payload);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());

        TopSecretResponse body = response.getBody();
        if (body == null) {
            throw new AssertionError("Response body is null");
        }
    }

    @Test
    public void testPositionToBeZero() {
        TopSecret controller = new TopSecret();

        SatelliteStatus[] statuses = new SatelliteStatus[]{
            new SatelliteStatus("kenobi", 538.52f, new String[]{"este", "", "", "mensaje", ""}),
            new SatelliteStatus("skywalker", 141.42f, new String[]{"", "es", "", "", "secreto"}),
            new SatelliteStatus("sato", 509.90f, new String[]{"este", "", "un", "", ""})
        };
        TopSecretRequest payload = new TopSecretRequest(statuses);
        var response = controller.DecodeEnemyInformation(payload);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());

        TopSecretResponse body = response.getBody();
        if (body == null) {
            throw new AssertionError("Response body is null");
        }
        assertNotNull(body.position);

        Point pos = body.position;
        assertTrue(pos.x >= -10 && pos.x <= 10);
        assertTrue(pos.y >= -10 && pos.y <= 10);
    }

    @Test
    public void testMessageBuild() {
        TopSecret controller = new TopSecret();

        SatelliteStatus[] statuses = new SatelliteStatus[]{
            new SatelliteStatus("kenobi", 538.52f, new String[]{"este", "", "", "mensaje", ""}),
            new SatelliteStatus("skywalker", 141.42f, new String[]{"", "es", "", "", "secreto"}),
            new SatelliteStatus("sato", 509.90f, new String[]{"este", "", "un", "", ""})
        };
        TopSecretRequest payload = new TopSecretRequest(statuses);
        var response = controller.DecodeEnemyInformation(payload);

        TopSecretResponse body = response.getBody();
        if (body == null) {
            throw new AssertionError("Response body is null");
        }
        assertEquals("este es un mensaje secreto", body.message);
    }
}
