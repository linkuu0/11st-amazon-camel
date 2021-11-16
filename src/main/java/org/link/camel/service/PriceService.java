package org.link.camel.service;

import lombok.RequiredArgsConstructor;
import org.link.camel.config.error.CustomException;
import org.link.camel.config.error.ErrorCode;
import org.link.camel.domain.Item;
import org.link.camel.repository.ItemRepository;
import org.link.camel.repository.PriceRepository;
import org.link.camel.web.PriceSaveRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final ItemRepository itemRepository;
    private final PriceRepository priceRepository;

    @Transactional(readOnly = true)
    public Long savePrice(PriceSaveRequest request) {
        Long productId = request.getProductId();
        Item product = itemRepository.findByProductId(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));

        return priceRepository.save(request.toEntity(product)).getId();
    }

}
