package org.link.camel.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.link.camel.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    void getItemInfoTest() {
        itemRepository.findByProductId(1000L);

    }

}