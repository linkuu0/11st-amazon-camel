package org.link.camel.web;

import lombok.Builder;
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
    private Long shipFee;
    private LocalDateTime timestamp;

    public Price toEntity(Item item) {
        return Price.builder()
                .item(item)
                .price(price)
                .shipFee(shipFee)
                .timestamp(timestamp)
                .build();
    }

    @Builder
    public PriceSaveRequest(Long productId, Long price, Long shipFee) {
        this.productId = productId;
        this.price = price;
        this.shipFee = shipFee;
        this.timestamp = LocalDateTime.now();
    }

}
