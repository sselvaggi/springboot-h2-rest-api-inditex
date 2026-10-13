package com.challenge.inditex.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TariffResponseDTO(
        Long productId,
        Long brandId,
        String brandName,
        Long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal price,
        String curr
) {
    public static TariffResponseDTO fromDomain(Tariff domain) {
        return new TariffResponseDTO(
                domain.productId(),
                domain.brandId(),
                Brand.getNameById(domain.brandId()),
                domain.priceList(),
                domain.startDate(),
                domain.endDate(),
                domain.price(),
                domain.curr()
        );
    }
}