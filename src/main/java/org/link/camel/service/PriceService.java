package org.link.camel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.link.camel.config.PriceHttpApiCode;
import org.link.camel.config.PriceHttpApiService;
import org.link.camel.config.ProductInfoResponse;
import org.link.camel.config.error.CustomException;
import org.link.camel.config.error.ErrorCode;
import org.link.camel.domain.Item;
import org.link.camel.repository.ItemRepository;
import org.link.camel.repository.PriceRepository;
import org.link.camel.web.PriceSaveRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceHttpApiService priceHttpApiService;
    private final ItemRepository itemRepository;
    private final PriceRepository priceRepository;

    @Value("${config.11st-api.key}")
    private String apiKey;

    @Transactional(readOnly = true)
    public Long savePrice(PriceSaveRequest request) {
        Long productId = request.getProductId();
        Item product = itemRepository.findByProductId(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));

        return priceRepository.save(request.toEntity(product)).getId();
    }

    public PriceSaveRequest getPriceInfo(Long productId) throws ExecutionException, InterruptedException {
        ProductInfoResponse response = priceHttpApiService.getPriceInfo(apiKey,
                PriceHttpApiCode.PRODUCT_INFO.getCode(), productId).get();

        if (response.getRequest() == null && response.getProduct() == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_PRODUCT_INFO_API);
        }

        // LowestPrice를 기록
        Long lowestPrice = Long.valueOf(response.getProduct().getProductPrice().lowestPrice
                .replace(",", "")
                .replace("원", ""));

        return PriceSaveRequest.builder()
                .productId(productId)
                .price(lowestPrice)
                .build();
    }
}
