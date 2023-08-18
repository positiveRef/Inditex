package com.positiveref.inditex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceData {
    // identificador de producto,
    // identificador de cadena,
    // tarifa a aplicar,
    // fechas de aplicación
    // y precio final a aplicar.
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long productId;
    private BigDecimal price;
    private String currency;
}
