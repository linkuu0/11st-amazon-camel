package org.link.camel.repository;

import org.link.camel.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("select p from Price p join fetch p.item")
    List<Price> findByItem(Long productId);
}
