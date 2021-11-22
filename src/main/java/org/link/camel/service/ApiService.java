package org.link.camel.service;


import com.linecorp.armeria.client.WebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.link.camel.api.ApiResponse;
import org.link.camel.api.ProductHttpApiCode;
import org.link.camel.api.ProductInfoResponse;
import org.link.camel.config.error.ErrorCode;
import org.link.camel.config.error.exception.ProductInfoNotFoundException;
import org.link.camel.config.error.exception.XmlDataBindException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.nio.charset.Charset;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiService {

    @Value("${config.11st-api.key}")
    private String apiKey;

    private final WebClient client;
    private final JAXBContext jaxbContext;

    public ProductInfoResponse searchProductInfoResponse(Long productId) {
        String content = getApiResult(productId, ProductHttpApiCode.PRODUCT_INFO);

        // ErrorResponse 존재 시 에러 처리
        if (content.indexOf("ErrorResponse") > 0) {
            log.info(content);
            throw new ProductInfoNotFoundException(ErrorCode.API_PRODUCT_INFO_NOT_FOUND, content);
        }

        return getResponseObject(content, jaxbContext);

    }

    private String getApiResult(Long productId, ProductHttpApiCode productHttpApiCode) {
        UriComponents path = UriComponentsBuilder.newInstance()
                .path("/openapi/OpenApiService.tmall")
                .queryParam("key", apiKey)
                .queryParam("apiCode", productHttpApiCode.getCode())
                .queryParam("productCode", productId)
                .build();

        return client.get(path.toString())
                .aggregate()
                .join()
                .content(Charset.forName("euc-kr"));
    }

    @SuppressWarnings("unchecked")
    private <T extends ApiResponse> T getResponseObject(String content, JAXBContext context) {
        T response;

        try (StringReader reader = new StringReader(content)) {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            response = (T) unmarshaller.unmarshal(reader);

            return response;

        } catch (JAXBException e) {
            throw new XmlDataBindException(ErrorCode.API_XML_BIND_ERROR);

        }

    }
}
