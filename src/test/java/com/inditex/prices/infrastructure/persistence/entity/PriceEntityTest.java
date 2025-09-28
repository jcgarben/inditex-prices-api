package com.inditex.prices.infrastructure.persistence.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PriceEntityTest {

    @Test
    void givenValidFieldValues_whenConstructingPriceEntity_thenAllFieldsAreAssigned() {
        Integer brandId = 1;
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        Integer priceList = 1;
        Long productId = 35455L;
        Integer priority = 0;
        BigDecimal price = new BigDecimal("35.50");
        String curr = "EUR";

        PriceEntity entity = new PriceEntity(brandId, startDate, endDate,
                priceList, productId, priority, price, curr);

        assertThat(entity.getBrandId()).isEqualTo(brandId);
        assertThat(entity.getStartDate()).isEqualTo(startDate);
        assertThat(entity.getEndDate()).isEqualTo(endDate);
        assertThat(entity.getPriceList()).isEqualTo(priceList);
        assertThat(entity.getProductId()).isEqualTo(productId);
        assertThat(entity.getPriority()).isEqualTo(priority);
        assertThat(entity.getPrice()).isEqualTo(price);
        assertThat(entity.getCurrency()).isEqualTo(curr);
    }

    @Test
    void givenAllNullValues_whenConstructingPriceEntity_thenAllFieldsRemainNull() {
        PriceEntity entity = new PriceEntity(null, null, null, null,
                null, null, null, null);

        assertThat(entity.getBrandId()).isNull();
        assertThat(entity.getStartDate()).isNull();
        assertThat(entity.getEndDate()).isNull();
        assertThat(entity.getPriceList()).isNull();
        assertThat(entity.getProductId()).isNull();
        assertThat(entity.getPriority()).isNull();
        assertThat(entity.getPrice()).isNull();
        assertThat(entity.getCurrency()).isNull();
    }

    @Test
    void givenNoArgsConstructor_whenInstantiatingPriceEntity_thenAllFieldsAreNull() {
        PriceEntity entity = new PriceEntity();

        assertThat(entity.getBrandId()).isNull();
        assertThat(entity.getStartDate()).isNull();
        assertThat(entity.getEndDate()).isNull();
        assertThat(entity.getPriceList()).isNull();
        assertThat(entity.getProductId()).isNull();
        assertThat(entity.getPriority()).isNull();
        assertThat(entity.getPrice()).isNull();
        assertThat(entity.getCurrency()).isNull();
    }
}
