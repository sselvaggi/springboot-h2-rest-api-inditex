package com.challenge.inditex.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Tariff(
        Long productId,
        Long brandId,
        Long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal price,
        String curr,
        Integer priority
) {}