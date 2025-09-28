package com.inditex.prices.application.service;

import com.inditex.prices.application.port.out.PriceRepository;
import com.inditex.prices.domain.model.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GetPriceUseCase {
    private final PriceRepository priceRepository;

    public GetPriceUseCase(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Optional<Price> execute(Integer brandId, Long productId, LocalDateTime date) {
        return priceRepository.findApplicablePrice(brandId, productId, date);
    }
}
