package com.quasar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quasar.model.SatelliteEntity;
import com.quasar.model.TopSecretRequest;
import com.quasar.model.Transmission;
import com.quasar.service.SatelliteService;
import com.quasar.service.TransmissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TopSecretTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SatelliteService satelliteService;

    @MockitoBean
    private TransmissionService transmissionService;


    @Test
    public void testDecodeEnemyInformation_success() throws Exception {
        SatelliteEntity kenobi = new SatelliteEntity("kenobi", 100.0, new String[]{"este", "", "", "mensaje", ""}, new Point(-500, -200));
        SatelliteEntity skywalker = new SatelliteEntity("skywalker", 115.5, new String[]{"", "es", "", "", "secreto"}, new Point(100, -100));
        SatelliteEntity sato = new SatelliteEntity("sato", 142.7, new String[]{"", "", "un", "", ""}, new Point(500, 100));

        when(satelliteService.findAndUpdateSatellite(eq("kenobi"), any())).thenReturn(kenobi);
        when(satelliteService.findAndUpdateSatellite(eq("skywalker"), any())).thenReturn(skywalker);
        when(satelliteService.findAndUpdateSatellite(eq("sato"), any())).thenReturn(sato);

        Transmission mockTransmission = new Transmission(new Point(-100, 75), "este es un mensaje secreto");
        when(transmissionService.calculateTransmission(any(), any(), any())).thenReturn(mockTransmission);

        SatelliteEntity[] input = {kenobi, skywalker, sato};
        TopSecretRequest request = new TopSecretRequest();
        request.satellites = input;

        mockMvc.perform(post("/topsecret/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position.x").value(-100))
                .andExpect(jsonPath("$.position.y").value(75))
                .andExpect(jsonPath("$.message").value("este es un mensaje secreto"));
    }

    @Test
    public void testDecodeEnemyInformation_badRequestIfNotThreeSatellites() throws Exception {
        SatelliteEntity[] input = {new SatelliteEntity("kenobi", 100.0, new String[]{"mensaje"}, null)};
        TopSecretRequest request = new TopSecretRequest();
        request.satellites = input;

        mockMvc.perform(post("/topsecret/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
