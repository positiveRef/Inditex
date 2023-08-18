package com.positiveref.inditex.service;

import com.positiveref.inditex.entity.PriceEntity;
import com.positiveref.inditex.model.PriceData;
import com.positiveref.inditex.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {
    private PriceService priceService;
    private PriceRepository priceRepository;

    @BeforeEach
    void init() {
        priceRepository = mock(PriceRepository.class);
        priceService = new PriceServiceImpl(priceRepository);
    }

    @Test
    void whenRepositoryReturnsMoreThanOneRegister_shouldFilterByPriorityAndReturnPrice() {
        when(priceRepository.findByDateAndProductIdAndBrandId(any(), any(), any()))
                .thenReturn(List.of(
                        PriceEntity.builder()
                                .price(BigDecimal.TEN)
                                .startDate(LocalDateTime.of(2020, 1, 1, 0, 0))
                                .endDate(LocalDateTime.of(2023, 1, 1, 0, 0))
                                .priority(0L)
                                .build(),
                        PriceEntity.builder()
                                .price(BigDecimal.ONE)
                                .startDate(LocalDateTime.of(2020, 2, 1, 0, 0))
                                .endDate(LocalDateTime.of(2023, 2, 1, 0, 0))
                                .priority(1L)
                                .build()
                ));
        PriceData priceData = priceService.findPrice(LocalDateTime.now(), 32L, 1L);

        assertThat(priceData).isNotNull();
        assertThat(priceData.getPrice()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    void whenRepositoryReturnsEmptyList_shouldReturnNullPrice() {
        when(priceRepository.findByDateAndProductIdAndBrandId(any(), any(), any()))
                .thenReturn(List.of());
        PriceData priceData = priceService.findPrice(LocalDateTime.now(), 32L, 1L);

        assertThat(priceData).isNull();
    }
}