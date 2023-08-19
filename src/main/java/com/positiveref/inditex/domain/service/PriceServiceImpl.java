package com.positiveref.inditex.domain.service;

import com.positiveref.inditex.domain.entity.PriceEntity;
import com.positiveref.inditex.domain.exception.NoSuchElementFoundException;
import com.positiveref.inditex.domain.model.PriceData;
import com.positiveref.inditex.domain.repository.PriceRepository;
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
            log.error("PriceData not found with given inputs");
            throw new NoSuchElementFoundException();
        }
        return priceData;
    }
}
