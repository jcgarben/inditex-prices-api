package com.inditex.prices.infrastructure.persistence;

import com.inditex.prices.infrastructure.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("""
                SELECT p 
                FROM PriceEntity p
                WHERE p.brandId = :brandId
                  AND p.productId = :productId
                  AND :applicationDate BETWEEN p.startDate AND p.endDate
            """)
    List<PriceEntity> findApplicablePrice(
            @Param("brandId") Integer brandId,
            @Param("productId") Long productId,
            @Param("applicationDate") LocalDateTime applicationDate
    );
}
