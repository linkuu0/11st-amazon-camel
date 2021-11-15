package org.link.camel.web;

import lombok.Getter;
import org.link.camel.domain.Item;

@Getter
public class ItemResponse {
    private Long id;
    private Long productId;
    private String name;

    public ItemResponse(Item entity) {
        this.id = entity.getId();
        this.productId = entity.getProductId();
        this.name = entity.getName();
    }
}
