package com.inditex.prices.application.service;

import com.inditex.prices.application.port.PriceRepositoryPort;
import com.inditex.prices.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class GetPriceUseCaseTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    private GetPriceUseCase getPriceUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getPriceUseCase = new GetPriceUseCase(priceRepositoryPort);
    }

    @Test
    void givenExistingApplicablePrice_whenExecutingUseCase_thenReturnsPrice() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price expectedPrice = new Price(1, 35455L, 1,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59),
                0, new BigDecimal("35.50"), "EUR");

        when(priceRepositoryPort.findApplicablePrices(1, 35455L, date))
                .thenReturn(List.of(expectedPrice));

        Optional<Price> result = getPriceUseCase.execute(1, 35455L, date);

        assertTrue(result.isPresent());
        assertEquals(expectedPrice, result.get());
    }

    @Test
    void givenNoApplicablePrice_whenExecutingUseCase_thenReturnsEmptyOptional() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        when(priceRepositoryPort.findApplicablePrices(1, 35455L, date))
                .thenReturn(Collections.emptyList());

        Optional<Price> result = getPriceUseCase.execute(1, 35455L, date);

        assertTrue(result.isEmpty());
    }
}