package org.link.camel.web;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.link.camel.domain.Item;

@Setter
@NoArgsConstructor
public class ItemRequest {

    private Long productId;

    @Builder
    public ItemRequest(Long productId) {
        this.productId = productId;
    }

    public Item toEntity() {
        return Item.builder()
                .productId(productId)
                .build();
    }
}
