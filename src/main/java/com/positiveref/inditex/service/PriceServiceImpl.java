package com.positiveref.inditex.service;

import com.positiveref.inditex.entity.PriceEntity;
import com.positiveref.inditex.model.PriceData;
import com.positiveref.inditex.repository.PriceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public PriceData findPrice(LocalDateTime petitionDate, Long productId, Long brandId) {
        final ModelMapper modelMapper = new ModelMapper();
        List<PriceEntity> priceEntities = priceRepository.findByDateAndProductIdAndBrandId(petitionDate, productId, brandId);
        log.debug("{} entities found", priceEntities.size());
        log.trace("Data from PriceRepository: {}", priceEntities);
        PriceData priceData = priceEntities.stream()
                .max(Comparator.comparing(PriceEntity::getPriority))
                .map(priceEntity -> modelMapper.map(priceEntity, PriceData.class))
                .orElse(null);
        if (priceData == null) {
            log.warn("PriceData not found with given inputs");
        }
        return priceData;
    }
}
