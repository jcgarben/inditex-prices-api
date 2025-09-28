package com.inditex.prices.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    void shouldCreateValidPrice() {
        LocalDateTime start = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 23, 59);

        Price price = new Price(1, 35455L, 1, start, end, 0,
                new BigDecimal("35.50"), "EUR");

        assertAll(
            () -> assertNotNull(price),
            () -> assertEquals(1, price.brandId()),
            () -> assertEquals(35455L, price.productId()),
            () -> assertEquals(new BigDecimal("35.50"), price.price()),
            () -> assertEquals("EUR", price.currency())
        );
    }
}