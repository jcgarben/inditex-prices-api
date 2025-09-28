package com.inditex.prices.application.port;

import com.inditex.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {
    List<Price> findApplicablePrices(Integer brandId, Long productId, LocalDateTime date);
}