package org.link.camel.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long productId;

    @Column
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column
    private String name;

    @OneToMany(mappedBy = "price")
    private List<Price> prices = new ArrayList<>();

    @Builder
    public Item(Long productId, ProductType productType, String name) {
        this.productId = productId;
        this.productType = productType;
        this.name = name;
    }
}
