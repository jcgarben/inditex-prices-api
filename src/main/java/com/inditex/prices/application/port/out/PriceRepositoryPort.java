package com.inditex.prices.application.port.out;

import com.inditex.prices.domain.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    Optional<Price> findApplicablePrice(Integer brandId, Long productId, LocalDateTime date);
}