package org.link.camel.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.link.camel.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    void getItemInfoTest() {
        itemRepository.findByProductId(1000L);

    }

}