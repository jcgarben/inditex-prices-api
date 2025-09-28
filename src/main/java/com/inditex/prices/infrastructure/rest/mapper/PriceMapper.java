package com.inditex.prices.infrastructure.rest.mapper;

import com.inditex.prices.domain.model.Price;
import com.inditex.prices.infrastructure.rest.dto.PriceResponse;

public class PriceMapper {

    public static PriceResponse toDto(Price price) {
        if (price == null) {
            return null;
        }

        PriceResponse response = new PriceResponse();
        response.setProductId(price.productId());
        response.setBrandId(price.brandId());
        response.setPriceList(price.priceList());
        response.setStartDate(price.startDate());
        response.setEndDate(price.endDate());
        response.setPrice(price.price());
        response.setCurrency(price.currency());

        return response;
    }
}