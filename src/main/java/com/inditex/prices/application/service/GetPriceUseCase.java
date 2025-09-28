package com.inditex.prices.application.service;

import com.inditex.prices.application.port.out.PriceRepositoryPort;
import com.inditex.prices.domain.model.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GetPriceUseCase {
    private final PriceRepositoryPort priceRepositoryPort;

    public GetPriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    public Optional<Price> execute(Integer brandId, Long productId, LocalDateTime date) {
        return priceRepositoryPort.findApplicablePrice(brandId, productId, date);
    }
}
