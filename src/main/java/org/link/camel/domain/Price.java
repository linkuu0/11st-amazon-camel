package org.link.camel.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime timestamp;

    @Column
    private Long price;

    @Column
    private Long shipFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void setItem(Item item) {
        if (this.item != null) {
            this.item.getPrices().remove(this);
        }

        this.item = item;
        item.getPrices().add(this);
    }

    @Builder
    public Price(Item item, Long price, Long shipFee, LocalDateTime timestamp) {
        this.item = item;
        this.price = price;
        this.shipFee = shipFee;
        this.timestamp = timestamp;
    }

}
