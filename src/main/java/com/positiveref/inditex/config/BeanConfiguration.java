package com.positiveref.inditex.config;

import com.positiveref.inditex.repository.PriceRepository;
import com.positiveref.inditex.service.PriceService;
import com.positiveref.inditex.service.PriceServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PriceService priceService(PriceRepository priceRepository) {
        return new PriceServiceImpl(priceRepository);
    }

}
