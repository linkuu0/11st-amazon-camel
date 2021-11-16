package org.link.camel.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.link.camel.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void saveTest() {
        Item item = Item.builder()
                .productId(1L)
                .name("테스트")
                .build();

        Item save = itemRepository.save(item);
        Assertions.assertThat(save.getProductId()).isEqualTo(1L);

    }

}