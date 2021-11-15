package org.link.camel.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {

    COMPUTER("컴퓨터");

    private String name;

}
