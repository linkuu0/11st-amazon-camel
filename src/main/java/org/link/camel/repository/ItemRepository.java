package org.link.camel.repository;

import org.link.camel.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);
    Optional<Item> findByProductId(Long productId);

}
