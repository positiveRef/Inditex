package com.positiveref.inditex.service;

import com.positiveref.inditex.model.PriceData;

import java.time.LocalDateTime;

public interface PriceService {

    PriceData findPrice(LocalDateTime petitionDate, Long productId, Long brandId);

}
