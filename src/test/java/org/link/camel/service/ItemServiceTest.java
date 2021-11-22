package org.link.camel.service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.link.camel.repository.ItemRepository;
import org.link.camel.web.ItemSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    @Autowired
    ItemRepository itemRepository;

    private ItemSaveRequest request;

    @BeforeAll
    void init() {
    }

    @Test
    void getItemInfoTest() {
        itemRepository.findByProductId(1000L);

    }

}