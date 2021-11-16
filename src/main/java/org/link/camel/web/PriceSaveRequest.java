package org.link.camel.web;

import lombok.Getter;
import lombok.Setter;
import org.link.camel.domain.Item;
import org.link.camel.domain.Price;

import java.time.LocalDateTime;

@Getter
@Setter
public class PriceSaveRequest {

    private Long productId;
    private Long price;
    private LocalDateTime timestamp;

    public Price toEntity(Item item) {
        return Price.builder()
                .item(item)
                .price(price)
                .build();

    }

}
