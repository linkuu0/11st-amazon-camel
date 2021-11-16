package org.link.camel.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.link.camel.domain.Item;
import org.link.camel.domain.Price;
import org.link.camel.repository.ItemRepository;
import org.link.camel.repository.PriceRepository;
import org.link.camel.web.PriceSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceServiceTest {

    @Autowired PriceService priceService;
    @Autowired PriceRepository priceRepository;

    @Autowired ItemRepository itemRepository;

    @Before
    public void initItem() {
        itemRepository.save(Item.builder()
                .productId(1L)
                .name("테스트")
                .build());
    }

    @Test
    @Transactional(readOnly = true)
    public void savePriceTest() {
        PriceSaveRequest request = new PriceSaveRequest();
        request.setPrice(10000L);
        request.setProductId(1L);

        Long id = priceService.savePrice(request);
        Price price = priceRepository.findById(id).get();

        Assertions.assertThat(price.getPrice()).isEqualTo(request.getPrice());
        Assertions.assertThat(price.getItem().getProductId()).isEqualTo(request.getProductId());
    }

}