package org.link.camel.api;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "ErrorResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApiErrorResponse implements ApiResponse {

    @XmlElement(name = "ErrorCode")
    private String errorCode;

    @XmlElement(name = "ErrorMessage")
    private String errorMessage;

    @XmlElement(name = "ErrorDetail")
    private String errorDetail;

}
