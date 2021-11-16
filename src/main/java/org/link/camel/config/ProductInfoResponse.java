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
        public String productCode;

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

}
