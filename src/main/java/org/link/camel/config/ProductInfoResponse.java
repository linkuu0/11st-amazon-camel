package org.link.camel.config;

import lombok.Getter;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * 11번가 상품정보 Response
 */
@Getter
@XmlRootElement(name = "ProductInfoResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductInfoResponse {

    @XmlElement(name = "Request")
    private Request request;

    @XmlElement(name = "Product")
    private Product product;

    @Getter
    static class Request {

        @XmlElementWrapper(name = "Arguments")
        @XmlElement(name = "Argument")
        private List<Argument> arguments;

        @XmlElement(name = "ProcessingTime")
        private String processingTime;

    }

    @Getter
    static class Product {

        @XmlElement(name = "ProductCode")
        private String productCode;

        @XmlElement(name = "ProductName")
        private String productName;

        @XmlElement(name = "ProductPrice")
        private ProductPrice productPrice;

        @XmlElement(name = "BasicImage")
        private String basicImage;

        @XmlElement(name = "AddImage1")
        private String addImage1;

        @XmlElement(name = "AddImage2")
        private String addImage2;

        @XmlElement(name = "AddImage3")
        private String addImage3;

        @XmlElement(name = "ImageL300")
        private String imageL300;

        @XmlElement(name = "Point")
        private Integer point;

        @XmlElement(name = "Chip")
        private Integer chip;

        @XmlElement(name = "Installment")
        private String installment;

        @XmlElement(name = "ShipFee")
        private String shipFee;

        @XmlElement(name = "SellSatisfaction")
        private Integer sellSatisfaction;

        @XmlElement(name = "SellGrade")
        private Integer sellGrade;
    }

    @Getter
    @XmlAccessorType(XmlAccessType.PROPERTY)
    static class Argument {

        @XmlAttribute
        private String name;

        @XmlAttribute
        private String value;
    }

    @Getter
    static class ProductPrice {

        @XmlElement(name = "Price")
        private String price;

        @XmlElement(name = "LowestPrice")
        private String lowestPrice;
    }

}
