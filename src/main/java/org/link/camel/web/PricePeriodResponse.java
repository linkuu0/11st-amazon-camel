package org.link.camel.web;

import lombok.Getter;
import org.link.camel.domain.Price;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class PricePeriodResponse {
    private String datetime;
    private Long price;

    public PricePeriodResponse(Price price) {
        this.datetime = datetimeToString(price.getTimestamp());
        this.price = price.getPrice();
    }

    public String datetimeToString(LocalDateTime datetime) {
        if (datetime == null) {
            return "";

        } else {
            return datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        }
    }
}
