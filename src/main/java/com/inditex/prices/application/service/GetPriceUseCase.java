package com.inditex.prices.application.service;

import com.inditex.prices.application.port.PriceRepositoryPort;
import com.inditex.prices.domain.model.Price;
import com.inditex.prices.domain.service.PriceSelector;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GetPriceUseCase {
    private final PriceRepositoryPort priceRepositoryPort;
    private final PriceSelector priceSelector = new PriceSelector();

    public GetPriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    public Optional<Price> execute(Integer brandId, Long productId, LocalDateTime date) {
        return priceSelector
                .selectApplicablePriceByPriority(priceRepositoryPort.findApplicablePrices(brandId, productId, date));
    }
}
