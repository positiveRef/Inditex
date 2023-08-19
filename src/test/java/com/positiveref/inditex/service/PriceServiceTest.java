package com.positiveref.inditex.service;

import com.positiveref.inditex.domain.service.PriceService;
import com.positiveref.inditex.domain.service.PriceServiceImpl;
import com.positiveref.inditex.domain.entity.PriceEntity;
import com.positiveref.inditex.domain.exception.NoSuchElementFoundException;
import com.positiveref.inditex.domain.model.PriceData;
import com.positiveref.inditex.domain.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        when(priceRepository.findByDateAndProductIdAndBrandId(any(), any(), any(), any()))
                .thenReturn(new PageImpl<>(List.of(
                        PriceEntity.builder()
                                .price(BigDecimal.ONE)
                                .startDate(LocalDateTime.of(2020, 2, 1, 0, 0))
                                .endDate(LocalDateTime.of(2023, 2, 1, 0, 0))
                                .priority(1L)
                                .build()
                )));
        PriceData priceData = priceService.findPrice(LocalDateTime.now(), 32L, 1L);

        assertThat(priceData).isNotNull();
        assertThat(priceData.getPrice()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    void whenRepositoryReturnsEmptyList_shouldThrowException() {
        LocalDateTime now = LocalDateTime.now();
        String expectedMessage = "Element not found";
        when(priceRepository.findByDateAndProductIdAndBrandId(any(), any(), any(), any()))
                .thenReturn(new PageImpl<>(List.of()));
        NoSuchElementFoundException exception = assertThrows(NoSuchElementFoundException.class,
                () -> priceService.findPrice(now, 32L, 1L));

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }
}