package com.quasar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quasar.model.SatelliteEntity;
import com.quasar.service.SatelliteService;
import org.hamcrest.Matchers;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TopSecretSplitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private SatelliteService satelliteService;

    @Test
    public void testUpdateSatellite_success() throws Exception {
        SatelliteEntity satellite = new SatelliteEntity("kenobi", 100.0, new String[]{"este", "es", "un", "mensaje", "secreto"}, null);

        when(satelliteService.saveOrUpdateSatellite(eq("kenobi"), any())).thenReturn(satellite);

        mockMvc.perform(post("/topsecret_split/kenobi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(satellite)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.distance").value(satellite.distance))
                .andExpect(jsonPath("$.message", Matchers.containsInAnyOrder("este", "es", "un", "mensaje", "secreto")));
    }
//    @GetMapping("/")
//    public ResponseEntity<Transmission> GetTriangulation() {
//        try {
//            SatelliteEntity kenobi = this.satelliteService.getSatellite("kenobi");
//            SatelliteEntity skywalker = this.satelliteService.getSatellite("skywalker");
//            SatelliteEntity sato = this.satelliteService.getSatellite("sato");
//            if (kenobi == null || skywalker == null || sato == null) {
//                return ResponseEntity.notFound().build();
//            }
//
//            Transmission newTransmission = this.transmissionService.calculateTransmission(kenobi, skywalker, sato);
//            return ResponseEntity.ok(newTransmission);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                    "Not enough information to determine position or message.");
//        }
//    }

    @Test
    public void testDecodeEnemyInformation_success() throws Exception {
        SatelliteEntity kenobi = new SatelliteEntity("kenobi", 2.0, new String[]{"este", "", "", "mensaje", ""}, new Point(1, 1));
        SatelliteEntity skywalker = new SatelliteEntity("skywalker", 2.0, new String[]{"", "es", "", "", "secreto"}, new Point(3, 3));
        SatelliteEntity sato = new SatelliteEntity("sato", 2.0, new String[]{"este", "", "un", "", ""}, new Point(5, 1));

        when(satelliteService.getSatellite(eq("kenobi"))).thenReturn(kenobi);
        when(satelliteService.getSatellite(eq("skywalker"))).thenReturn(skywalker);
        when(satelliteService.getSatellite(eq("sato"))).thenReturn(sato);

        mockMvc.perform(get("/topsecret_split/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position.x").value(3))
                .andExpect(jsonPath("$.position.y").value(1))
                .andExpect(jsonPath("$.message").value("este es un mensaje secreto"));
    }

    @Test
    public void testDecodeEnemyInformation_notSatellites() throws Exception {
        SatelliteEntity kenobi = new SatelliteEntity("kenobi", 2.0, new String[]{"este", "", "", "mensaje", ""}, new Point(1, 1));

        when(satelliteService.getSatellite(eq("kenobi"))).thenReturn(kenobi);
        when(satelliteService.getSatellite(eq("skywalker"))).thenReturn(null);
        when(satelliteService.getSatellite(eq("sato"))).thenReturn(null);

        mockMvc.perform(get("/topsecret_split/"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDecodeEnemyInformation_wrongInformation() throws Exception {
        SatelliteEntity kenobi = new SatelliteEntity("kenobi", 2.0, new String[]{"este", "", "", "mensaje", ""}, new Point(100, 100));
        SatelliteEntity skywalker = new SatelliteEntity("skywalker", 2.0, new String[]{"", "es", "", "", "secreto"}, new Point(300, 300));
        SatelliteEntity sato = new SatelliteEntity("sato", 2.0, new String[]{"este", "", "un", "", ""}, new Point(500, 100));

        when(satelliteService.getSatellite(eq("kenobi"))).thenReturn(kenobi);
        when(satelliteService.getSatellite(eq("skywalker"))).thenReturn(skywalker);
        when(satelliteService.getSatellite(eq("sato"))).thenReturn(sato);

        mockMvc.perform(get("/topsecret_split/"))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Not enough information to determine position or message."));

    }
}
