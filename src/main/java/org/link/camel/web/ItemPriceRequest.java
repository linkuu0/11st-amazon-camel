package org.link.camel.web;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class ItemPriceRequest {

    private Long productId;
    private Integer price;

}
