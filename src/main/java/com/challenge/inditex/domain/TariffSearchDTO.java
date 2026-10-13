package com.challenge.inditex.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record TariffSearchDTO(
        @Positive(message = "Brand ID must be positive")
        long brandId,

        @Positive(message = "Product ID must be positive")
        long productId,

        @NotNull(message = "Datetime cannot be null")
        @JsonFormat(pattern = "yyyy-MM-dd-HH.mm.ss")
        LocalDateTime datetime
) {
}