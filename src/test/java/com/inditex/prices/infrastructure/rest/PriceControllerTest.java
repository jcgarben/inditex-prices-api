package com.inditex.prices.infrastructure.rest;

import com.inditex.prices.application.service.GetPriceUseCase;
import com.inditex.prices.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceControllerTest {

    @Mock
    private GetPriceUseCase getPriceUseCase;

    private PriceController priceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        priceController = new PriceController(getPriceUseCase);
    }

    @Test
    void shouldReturn200WhenPriceExists() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price price = new Price(1, 35455L, 1,
            LocalDateTime.of(2020, 6, 14, 0, 0),
            LocalDateTime.of(2020, 12, 31, 23, 59),
            0, new BigDecimal("35.50"), "EUR");

        when(getPriceUseCase.execute(1, 35455L, date))
            .thenReturn(Optional.of(price));

        ResponseEntity<?> response = priceController.getPrice(date, 35455L, 1);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void shouldReturn404WhenPriceNotExists() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        when(getPriceUseCase.execute(1, 35455L, date))
            .thenReturn(Optional.empty());

        ResponseEntity<?> response = priceController.getPrice(date, 35455L, 1);

        assertEquals(404, response.getStatusCodeValue());
    }
}
