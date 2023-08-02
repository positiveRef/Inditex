package com.positiveref.inditex.service;

import com.positiveref.inditex.entity.PriceEntity;
import com.positiveref.inditex.model.Price;
import com.positiveref.inditex.repository.PriceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Price findPrice(LocalDateTime petitionDate, Long productId, Long brandId) {

        List<PriceEntity> byDateAndProductIdAndBrandId = priceRepository.findByDateAndProductIdAndBrandId(petitionDate, productId, brandId);
        log.debug("{} registers found", byDateAndProductIdAndBrandId.size());
        log.trace("Registers from PriceRepository: {}", byDateAndProductIdAndBrandId);
        Price price = byDateAndProductIdAndBrandId.stream()
                .max(Comparator.comparing(PriceEntity::getPriority))
                .map(priceEntity -> modelMapper.map(priceEntity, Price.class))
                .orElse(null);
        if (price == null) {
            log.warn("Price not found with given inputs");
        }
        return price;
    }
}
