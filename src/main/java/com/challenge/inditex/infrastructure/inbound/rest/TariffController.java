package com.challenge.inditex.infrastructure.inbound.rest;

import com.challenge.inditex.domain.Tariff;

import com.challenge.inditex.application.service.TariffService;
import com.challenge.inditex.domain.TariffSearchDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TariffController {

    private final TariffService tariffService;

    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @PostMapping(value = "/api/tariff/search", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Tariff> searchTariff(@RequestBody @Valid TariffSearchDTO tariffSearchDTO) {
        Optional<Tariff> result = tariffService.search(tariffSearchDTO);
        return ResponseEntity.of(result);
    }
}