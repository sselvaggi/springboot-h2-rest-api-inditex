package com.challenge.inditex.controller;

import com.challenge.inditex.WebApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TariffControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void tariffSearch1() throws Exception {
        mockMvc.perform(post("/api/tariff/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"brandId\":1, \"productId\": 35455, \"datetime\": \"2020-06-14-10.00.00\"}")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("price").value(35.50))
                .andExpect(jsonPath("priceList").value(1));
    }

    @Test
    void tariffSearch2() throws Exception {
        mockMvc.perform(post("/api/tariff/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"brandId\":1, \"productId\": 35455, \"datetime\": \"2020-06-14-16.00.00\"}")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("price").value(25.45))
                .andExpect(jsonPath("priceList").value(2));
    }

    @Test
    void tariffSearch3() throws Exception {
        mockMvc.perform(post("/api/tariff/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"brandId\":1, \"productId\": 35455, \"datetime\": \"2020-06-14-21.00.00\"}")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("price").value(35.50))
                .andExpect(jsonPath("priceList").value(1));
    }

    @Test
    void tariffSearch4() throws Exception {
        mockMvc.perform(post("/api/tariff/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"brandId\":1, \"productId\": 35455, \"datetime\": \"2020-06-15-10.00.00\"}")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("price").value(30.50))
                .andExpect(jsonPath("priceList").value(3));
    }

    @Test
    void tariffSearch5() throws Exception {
        mockMvc.perform(post("/api/tariff/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"brandId\":1, \"productId\": 35455, \"datetime\": \"2020-06-16-21.00.00\"}")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("price").value(38.95))
                .andExpect(jsonPath("priceList").value(4));
    }

    @Test
    void tariffSearchNonExistent() throws Exception {
        mockMvc.perform(post("/api/tariff/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"brandId\":1, \"productId\": 35455, \"datetime\": \"1800-06-16-21.00.00\"}")
        ).andExpect(status().isNotFound());
    }

    @Test
    void tariffSearchBadRequest() throws Exception {
        mockMvc.perform(post("/api/tariff/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"brandId\": -1, \"productId\": 35455, \"datetime\": \"2020-06-14-10.00.00\"}")
        ).andExpect(status().isBadRequest());
    }
}