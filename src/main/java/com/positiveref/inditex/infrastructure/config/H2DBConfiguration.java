package com.positiveref.inditex.infrastructure.config;

import com.positiveref.inditex.domain.repository.PriceRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = PriceRepository.class)
public class H2DBConfiguration {
}
