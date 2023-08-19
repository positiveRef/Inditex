package com.positiveref.inditex.controller;

import com.positiveref.inditex.application.controller.PriceController;
import com.positiveref.inditex.domain.model.PriceData;
import com.positiveref.inditex.domain.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @InjectMocks
    private PriceController priceController;

    @Mock
    private PriceService priceService;

    @Test
    void shouldReturnResponseEntityOk() {
        LocalDateTime dateTime = LocalDateTime.of(2020, 1, 1, 1, 1);
        when(priceService.findPrice(dateTime, 1L, 1L))
                .thenReturn(PriceData.builder().price(BigDecimal.ZERO).build());
        ResponseEntity<PriceData> price = priceController.getPrice(dateTime, 1L, 1L);
        assertThat(price.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(price.getBody()).isNotNull();
        assertThat(price.getBody().getPrice()).isEqualTo(BigDecimal.ZERO);
    }
}