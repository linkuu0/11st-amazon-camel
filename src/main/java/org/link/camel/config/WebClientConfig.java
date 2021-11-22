package org.link.camel.config;

import com.linecorp.armeria.client.ClientFactory;
import com.linecorp.armeria.client.ClientFactoryBuilder;
import com.linecorp.armeria.client.WebClient;
import com.linecorp.armeria.client.retrofit2.ArmeriaRetrofit;
import org.link.camel.api.ApiErrorResponse;
import org.link.camel.api.ProductInfoResponse;
import org.link.camel.service.ProductHttpApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import javax.xml.bind.*;

@Configuration
public class WebClientConfig {

    @Value("${config.11st-api.url}")
    private String apiUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.of(apiUrl);
    }

    @Bean
    public JAXBContext jaxbContext() throws JAXBException {
        return JAXBContext.newInstance(ProductInfoResponse.class, ApiErrorResponse.class);
    }

    @Bean
    public Retrofit retrofit() {
        return ArmeriaRetrofit.builder(apiUrl)
                .addConverterFactory(JaxbConverterFactory.create())
                .build();
    }

    @Bean
    public ProductHttpApiService productHttpApiService() {
        Retrofit retrofit = retrofit();
        return retrofit.create(ProductHttpApiService.class);
    }

}
