package org.link.camel.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.link.camel.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void saveTest() {
        Item item = Item.builder()
                .name("테스트")
                .build();

        Item save = itemRepository.save(item);
        Assertions.assertThat(save.getName()).isEqualTo(item.getName());

    }

}