package com.inditex.prices.infrastructure.persistence;

import java.io.Serializable;
import java.util.Objects;

public class PriceId implements Serializable {

    private Integer brandId;
    private Integer priceList;
    private Long productId;

    public PriceId() {
    }

    public PriceId(Integer brandId, Integer priceList, Long productId) {
        this.brandId = brandId;
        this.priceList = priceList;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceId that)) return false;
        return Objects.equals(brandId, that.brandId)
                && Objects.equals(priceList, that.priceList)
                && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, priceList, productId);
    }
}
