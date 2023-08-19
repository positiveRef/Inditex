package com.positiveref.inditex.domain.service;

import com.positiveref.inditex.domain.model.PriceData;

import java.time.LocalDateTime;

public interface PriceService {

    PriceData findPrice(LocalDateTime petitionDate, Long productId, Long brandId);

}
