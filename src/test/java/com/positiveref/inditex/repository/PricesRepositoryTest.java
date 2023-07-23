package com.positiveref.inditex.repository;

import com.positiveref.inditex.entity.PricesEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PricesRepositoryTest {

    @Autowired
    private PricesRepository pricesRepository;

    @Test
    void dbLoadsAllData() {
        long all = pricesRepository.count();
        assertThat(all).isEqualTo(4);
    }

    @Test
    void givenDateTimeProductIdAndBrandIdShouldFindTwoPriceEntities() {
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 6, 14), LocalTime.of(15, 0, 1));
        List<PricesEntity> entities = pricesRepository.findByDateAndProductIdAndBrandId(dateTime, 35455L, 1L);
        assertThat(entities).hasSize(2);
        //TODO
//        assertThat(entities).isEqualTo(List.of(PricesEntity.builder()
//                .brandId(1L)
//                .price(BigDecimal.valueOf(35.50))
//        ))
    }
}
