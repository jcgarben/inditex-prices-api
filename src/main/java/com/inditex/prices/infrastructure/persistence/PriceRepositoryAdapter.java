package com.inditex.prices.infrastructure.persistence;

import com.inditex.prices.application.port.out.PriceRepositoryPort;
import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.persistence.entity.PriceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final JpaPriceRepository jpaDataPriceRepository;

    @Override
    public Optional<Price> findApplicablePrice(Integer brandId, Long productId, LocalDateTime applicationDate) {
        return jpaDataPriceRepository.findApplicablePrice(brandId, productId, applicationDate)
                .stream()
                .map(this::toDomain)
                .findFirst();
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