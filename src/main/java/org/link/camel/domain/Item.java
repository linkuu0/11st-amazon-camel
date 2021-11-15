package org.link.camel.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue()
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

}
