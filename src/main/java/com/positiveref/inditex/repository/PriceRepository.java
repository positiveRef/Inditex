package com.positiveref.inditex.repository;

import com.positiveref.inditex.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query(value = "SELECT p FROM PricesEntity as p " +
            "where p.startDate <= ?1 and p.endDate >= ?1 and p.productId = ?2 and p.brandId = ?3 order by p.priority desc")
    List<PriceEntity> findByDateAndProductIdAndBrandId(LocalDateTime dateTime, Long productId, Long brandId);
}
