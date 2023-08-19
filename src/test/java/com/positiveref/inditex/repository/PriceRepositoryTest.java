package com.positiveref.inditex.repository;

import com.positiveref.inditex.domain.entity.PriceEntity;
import com.positiveref.inditex.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    @Test
    void dbLoadsAllData() {
        long count = priceRepository.count();
        assertThat(count).isEqualTo(4);
    }

    @Test
    void givenDateTimeProductIdAndBrandIdShouldFindTwoPriceEntities() {
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 6, 14), LocalTime.of(15, 0, 1));
        long productId = 35455L;
        Page<PriceEntity> entities = priceRepository.findByDateAndProductIdAndBrandId(dateTime, productId, 1L, Pageable.ofSize(1));
        assertThat(entities).hasSize(1);
    }
}
