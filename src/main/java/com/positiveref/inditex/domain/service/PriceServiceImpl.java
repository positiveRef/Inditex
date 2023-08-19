package com.positiveref.inditex.domain.service;

import com.positiveref.inditex.domain.entity.PriceEntity;
import com.positiveref.inditex.domain.exception.NoSuchElementFoundException;
import com.positiveref.inditex.domain.model.PriceData;
import com.positiveref.inditex.domain.repository.PriceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

@Slf4j
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public PriceData findPrice(LocalDateTime petitionDate, Long productId, Long brandId) {
        final ModelMapper modelMapper = new ModelMapper();
        Page<PriceEntity> priceEntities = priceRepository.findByDateAndProductIdAndBrandId(petitionDate, productId, brandId, PageRequest.ofSize(1));
        log.debug("{} entities found", priceEntities.getSize());
        log.trace("Data from PriceRepository: {}", priceEntities);
        return priceEntities.stream()
                .map(priceEntity -> modelMapper.map(priceEntity, PriceData.class))
                .findFirst()
                .orElseThrow(NoSuchElementFoundException::new);
    }
}
