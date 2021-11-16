package org.link.camel.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PriceHttpApiCode {
    PRODUCT_SEARCH("ProductSearch", "상품검색"),
    CATEGORY_INFO("CategoryInfo", "카테고리 정보"),
    PRODUCT_INFO("ProductInfo", "상품정보"),
    PRODUCT_IMAGE("ProductImage", "상품정보 이미지");

    private final String code;
    private final String description;
}
