package com.inditex.prices.infrastructure.rest;

import com.inditex.prices.application.service.GetPriceUseCase;
import com.inditex.prices.domain.exception.PriceNotFoundException;
import com.inditex.prices.infrastructure.rest.dto.PriceResponse;
import com.inditex.prices.infrastructure.rest.mapper.PriceMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/price")
public class PriceController {
    private final GetPriceUseCase getPriceUseCase;

    public PriceController(GetPriceUseCase getPriceUseCase) {
        this.getPriceUseCase = getPriceUseCase;
    }

    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam Integer brandId,
            @RequestParam Long productId,
            @RequestParam LocalDateTime date
    ) {

        return getPriceUseCase.execute(brandId, productId, date)
                .map(PriceMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseThrow(PriceNotFoundException::new);
    }
}