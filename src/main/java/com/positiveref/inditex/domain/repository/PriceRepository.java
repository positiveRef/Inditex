package com.positiveref.inditex.domain.repository;

import com.positiveref.inditex.domain.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query(value = "SELECT p FROM PriceEntity as p " +
            "where p.startDate <= ?1 and p.endDate >= ?1 and p.productId = ?2 and p.brandId = ?3")
    List<PriceEntity> findByDateAndProductIdAndBrandId(LocalDateTime dateTime, Long productId, Long brandId);
}
