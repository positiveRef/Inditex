package com.positiveref.inditex.domain.repository;

import com.positiveref.inditex.domain.entity.PriceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query(value = "SELECT p FROM PriceEntity AS p " +
            "WHERE p.startDate <= ?1 AND p.endDate >= ?1 AND p.productId = ?2 AND p.brandId = ?3 " +
            "ORDER BY p.priority DESC")
    Page<PriceEntity> findByDateAndProductIdAndBrandId(LocalDateTime dateTime, Long productId, Long brandId, Pageable pageable);
}
