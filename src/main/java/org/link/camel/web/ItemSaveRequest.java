package org.link.camel.web;

import lombok.Builder;
import lombok.Setter;
import org.link.camel.domain.Item;
import org.link.camel.domain.ProductType;

import javax.validation.constraints.NotNull;

@Setter
public class ItemSaveRequest {

    @NotNull
    private Long productId;

    private ProductType productType;

    @NotNull
    private String name;

    @Builder
    public ItemSaveRequest(Long productId, ProductType productType, String name) {
        this.productId = productId;
        this.productType = productType;
        this.name = name;
    }

    public Item toEntity() {
        return Item.builder()
                .productId(productId)
                .productType(productType)
                .name(name)
                .build();
    }

}
