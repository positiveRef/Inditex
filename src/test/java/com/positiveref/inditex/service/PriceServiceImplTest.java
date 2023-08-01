package com.positiveref.inditex.service;

import com.positiveref.inditex.entity.PriceEntity;
import com.positiveref.inditex.model.Price;
import com.positiveref.inditex.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @InjectMocks
    private PriceServiceImpl pricesService;

    @Mock
    private PriceRepository priceRepository;

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
        Price price = pricesService.findPrice(LocalDateTime.now(), 32L, 1L);

        assertThat(price).isNotNull();
        assertThat(price.getPrice()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    void whenRepositoryReturnsEmptyList_shouldReturnNullPrice(){
        when(priceRepository.findByDateAndProductIdAndBrandId(any(), any(), any()))
                .thenReturn(List.of());
        Price price = pricesService.findPrice(LocalDateTime.now(), 32L, 1L);

        assertThat(price).isNull();
    }
}