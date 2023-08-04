package com.positiveref.inditex.controller;

import com.positiveref.inditex.model.Price;
import com.positiveref.inditex.service.PriceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("inditex")
@AllArgsConstructor
@Slf4j
public class PriceController {

    private final PriceService priceService;

    @GetMapping(value = "/price")
    public ResponseEntity<Price> getPrice(@RequestParam
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                          LocalDateTime dateTime,
                                          @RequestParam
                                          Long productId,
                                          @RequestParam
                                          Long brandId) {

        log.info("Received request through GET inditex/price with parameters dateTime: {}, productId: {}, brandId: {}",
                dateTime, productId, brandId);
        return ResponseEntity.ok(priceService.findPrice(dateTime, productId, brandId));
    }
}
