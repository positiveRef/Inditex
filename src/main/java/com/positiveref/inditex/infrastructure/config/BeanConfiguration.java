package com.positiveref.inditex.infrastructure.config;

import com.positiveref.inditex.domain.repository.PriceRepository;
import com.positiveref.inditex.domain.service.PriceService;
import com.positiveref.inditex.domain.service.PriceServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PriceService priceService(PriceRepository priceRepository) {
        return new PriceServiceImpl(priceRepository);
    }

}
