package com.inditex.prices.infrastructure.persistence;

import com.inditex.prices.application.port.PriceRepositoryPort;
import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.persistence.entity.PriceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final JpaPriceRepository jpaDataPriceRepository;

    @Override
    public List<Price> findApplicablePrices(Integer brandId, Long productId, LocalDateTime date) {
        return jpaDataPriceRepository.findApplicablePrices(brandId, productId, date)
                .stream()
                .map(this::toDomain)
                .toList();

    }

    private Price toDomain(PriceEntity entity) {
        return new Price(
                entity.getBrandId(),
                entity.getProductId(),
                entity.getPriceList(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurrency()
        );
    }
}