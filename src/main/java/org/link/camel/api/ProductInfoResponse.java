package org.link.camel.api;

import lombok.Getter;
import org.link.camel.web.ItemSaveRequest;
import org.link.camel.web.PriceSaveRequest;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * 11번가 상품정보 Response
 */
@Getter
@XmlRootElement(name = "ProductInfoResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductInfoResponse implements ApiResponse {

    @XmlElement(name = "Request")
    public Request request;

    @XmlElement(name = "Product")
    public Product product;

    @Getter
    static class Request {

        @XmlElementWrapper(name = "Arguments")
        @XmlElement(name = "Argument")
        public List<Argument> arguments;

        @XmlElement(name = "ProcessingTime")
        public String processingTime;

    }

    @Getter
    public static class Product {

        @XmlElement(name = "ProductCode")
        public Long productCode;

        @XmlElement(name = "ProductName")
        public String productName;

        @XmlElement(name = "ProductPrice")
        public ProductPrice productPrice;

        @XmlElement(name = "BasicImage")
        public String basicImage;

        @XmlElement(name = "AddImage1")
        public String addImage1;

        @XmlElement(name = "AddImage2")
        public String addImage2;

        @XmlElement(name = "AddImage3")
        public String addImage3;

        @XmlElement(name = "ImageL300")
        public String imageL300;

        @XmlElement(name = "Point")
        public Integer point;

        @XmlElement(name = "Chip")
        public Integer chip;

        @XmlElement(name = "Installment")
        public String installment;

        @XmlElement(name = "ShipFee")
        public String shipFee;

        @XmlElement(name = "SellSatisfaction")
        public Integer sellSatisfaction;

        @XmlElement(name = "SellGrade")
        public Integer sellGrade;
    }

    @Getter
    @XmlAccessorType(XmlAccessType.PROPERTY)
    public static class Argument {

        @XmlAttribute
        public String name;

        @XmlAttribute
        public String value;
    }

    @Getter
    public static class ProductPrice {

        @XmlElement(name = "Price")
        public String price;

        @XmlElement(name = "LowestPrice")
        public String lowestPrice;
    }

    public Long getLowestPrice() {
        String lowestPrice = this.product.getProductPrice().getLowestPrice();

        return convertStringToLong(lowestPrice);
    }

    public Long getShipFee() {
        String shipFee = this.product.getShipFee();

        return convertStringToLong(shipFee);
    }

    public Long convertStringToLong(String value) {
        value = value.replaceAll("\\D", "");
        if ("".equals(value))
            return 0L;

        return Long.valueOf(value);
    }

    public ItemSaveRequest toItemSaveRequest() {
        return ItemSaveRequest.builder()
                .productId(this.product.getProductCode())
                .productType(null)
                .name(this.product.getProductName())
                .build();
    }

    public PriceSaveRequest toPriceSaveRequest() {
        return PriceSaveRequest.builder()
                .productId(this.product.getProductCode())
                .price(getLowestPrice())
                .shipFee(getShipFee())
                .build();
    }

}
