package org.link.camel.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.link.camel.domain.Price;
import org.link.camel.repository.ItemRepository;
import org.link.camel.repository.PriceRepository;
import org.link.camel.web.PriceSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PriceServiceTest {

    @Autowired PriceService priceService;
    @Autowired PriceRepository priceRepository;
    @Autowired ItemRepository itemRepository;

    @BeforeAll
    public static void initItem() {
//        itemRepository.save(Item.builder()
//                .productId(1L)
//                .name("테스트")
//                .build());
    }

    @Test
    public void getPriceList() {

    }

    @Test
    @Transactional(readOnly = true)
    public void savePriceTest() {
        PriceSaveRequest request = PriceSaveRequest.builder()
                .price(10000L)
                .productId(3548741298L)
                .build();

        Long id = priceService.savePrice(request);
        Price price = priceRepository.findById(id).get();

        Assertions.assertThat(price.getPrice()).isEqualTo(request.getPrice());
        Assertions.assertThat(price.getItem().getProductId()).isEqualTo(request.getProductId());
    }

    @Test
    public void savePriceScheduledTest() {
        priceService.savePriceScheduled();
    }

}