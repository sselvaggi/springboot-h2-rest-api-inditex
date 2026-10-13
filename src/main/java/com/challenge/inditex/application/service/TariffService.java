package com.challenge.inditex.application.service;

import com.challenge.inditex.domain.Tariff;
import com.challenge.inditex.domain.TariffSearchDTO;
import com.challenge.inditex.infrastructure.outbound.persistence.TariffRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TariffService {

    private final TariffRepository repository;

    public TariffService(TariffRepository repository) {
        this.repository = repository;
    }

    public Optional<Tariff> search(TariffSearchDTO tariffSearchDTO) {
        return repository.search(tariffSearchDTO);
    }
}