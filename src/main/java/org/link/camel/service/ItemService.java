package org.link.camel.service;


import lombok.RequiredArgsConstructor;
import org.link.camel.config.error.CustomException;
import org.link.camel.config.error.ErrorCode;
import org.link.camel.domain.Item;
import org.link.camel.repository.ItemRepository;
import org.link.camel.web.ChartResponse;
import org.link.camel.web.ItemPriceRequest;
import org.link.camel.web.ItemRequest;
import org.link.camel.web.ItemResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public ItemResponse getProductInfo(Long productId) {
        Item item = itemRepository.findByProductId(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));

        return new ItemResponse(item);
    }

    @Transactional(readOnly = true)
    public ChartResponse getChartData(Long id) {
        return null;
    }

    @Transactional
    public Long saveItem(ItemRequest request) {
        return itemRepository.save(request.toEntity()).getId();
    }

    @Transactional
    public Long saveItemPrice(ItemPriceRequest request) {


        return 0L;
    }


}
