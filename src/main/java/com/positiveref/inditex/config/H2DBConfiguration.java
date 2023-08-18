package com.positiveref.inditex.config;

import com.positiveref.inditex.repository.PriceRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = PriceRepository.class )
public class H2DBConfiguration {
}
