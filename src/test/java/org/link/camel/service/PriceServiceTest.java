package org.link.camel.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.link.camel.api.ProductHttpApiCode;
import org.link.camel.api.ProductInfoResponse;
import org.link.camel.domain.Item;
import org.link.camel.domain.Price;
import org.link.camel.repository.ItemRepository;
import org.link.camel.repository.PriceRepository;
import org.link.camel.web.PriceSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceServiceTest {

    @Autowired PriceService priceService;
    @Autowired PriceRepository priceRepository;

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ProductHttpApiService apiService;

    @Autowired Executor asyncExecutor;

    @Value("${config.11st-api.key}")
    String apiKey;


    @Before
    public void initItem() {
//        itemRepository.save(Item.builder()
//                .productId(1L)
//                .name("테스트")
//                .build());
    }

    @Test
    @Transactional(readOnly = true)
    public void savePriceTest() {
        PriceSaveRequest request = PriceSaveRequest.builder()
                .price(10000L)
                .productId(1L)
                .build();

        Long id = priceService.savePrice(request);
        Price price = priceRepository.findById(id).get();

        Assertions.assertThat(price.getPrice()).isEqualTo(request.getPrice());
        Assertions.assertThat(price.getItem().getProductId()).isEqualTo(request.getProductId());
    }

    @Test
    public void getPriceInfoTest() throws ExecutionException, InterruptedException {
        Long productId = 3545503877L;

        ProductInfoResponse response = apiService.getProductInfo(apiKey, ProductHttpApiCode.PRODUCT_INFO.getCode(), productId).get();
        System.out.println("response = " + response);
    }

    @Test
    public void savePriceScheduledTest() {
        priceService.savePriceScheduled();
    }

}