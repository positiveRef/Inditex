package com.positiveref.inditex.service;

import com.positiveref.inditex.model.Price;

import java.time.LocalDateTime;

public interface PriceService {

    Price findPrice(LocalDateTime petitionDate, Long productId, Long brandId);

}
