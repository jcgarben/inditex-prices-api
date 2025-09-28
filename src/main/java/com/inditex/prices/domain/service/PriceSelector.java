package com.inditex.prices.domain.service;

import com.inditex.prices.domain.model.Price;

import java.util.List;
import java.util.Optional;

public class PriceSelector {

    public Optional<Price> selectApplicablePriceByPriority(List<Price> candidates) {
        return candidates.stream()
                .max((p1, p2) -> Integer.compare(p1.priority(), p2.priority()));
    }
}
