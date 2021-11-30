package org.link.camel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.link.camel.api.ProductInfoResponse;
import org.link.camel.config.error.ErrorCode;
import org.link.camel.config.error.exception.EntityNotFoundException;
import org.link.camel.domain.Item;
import org.link.camel.domain.Price;
import org.link.camel.repository.ItemRepository;
import org.link.camel.repository.PriceRepository;
import org.link.camel.web.PricePeriodRequest;
import org.link.camel.web.PriceSaveRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceService {

    private final ApiService productService;
    private final ItemRepository itemRepository;
    private final PriceRepository priceRepository;

    private final Executor asyncExecutor;

    @Transactional(readOnly = false)
    public List<Price> getPriceList(Long productId, PricePeriodRequest request) {
        // request 추후 구현
        return priceRepository.findByItem(productId);
    }

    @Transactional(readOnly = true)
    public Long savePrice(PriceSaveRequest request) {
        Long productId = request.getProductId();
        Item item = itemRepository.findByProductId(productId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ITEM_NOT_FOUND));

        return priceRepository.save(request.toEntity(item)).getId();
    }

    @Transactional(readOnly = true)
    public void savePriceScheduled() {
        List<Price> prices = itemRepository.findAll().stream()
                .map(this::savePriceAsync)
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .collect(Collectors.toUnmodifiableList());

        priceRepository.saveAll(prices);
    }

    @Async
    public CompletableFuture<Price> savePriceAsync(Item item) {
        return CompletableFuture.supplyAsync(() -> {
            ProductInfoResponse response = productService.searchProductInfoResponse(item.getProductId());
            PriceSaveRequest request = response.toPriceSaveRequest();
            return request.toEntity(item);
        }, asyncExecutor);
    }

    public PriceSaveRequest getPriceInfo(Long productId) {
        ProductInfoResponse response = productService.searchProductInfoResponse(productId);

        return PriceSaveRequest.builder()
                .productId(productId)
                .price(response.getLowestPrice())
                .shipFee(response.getShipFee())
                .build();
    }
}
