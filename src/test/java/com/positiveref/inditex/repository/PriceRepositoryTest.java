package com.positiveref.inditex.repository;

import com.positiveref.inditex.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
        List<PriceEntity> entities = priceRepository.findByDateAndProductIdAndBrandId(dateTime, productId, 1L);
        assertThat(entities).hasSize(2);
    }
}
