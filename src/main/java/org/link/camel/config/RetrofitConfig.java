package org.link.camel.config;

import com.linecorp.armeria.client.retrofit2.ArmeriaRetrofit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

@Configuration
public class RetrofitConfig {

    @Value("${config.11st-api.url}")
    private String apiUrl;

    @Bean
    public Retrofit retrofit() {
        return ArmeriaRetrofit.builder(apiUrl)
                .addConverterFactory(JaxbConverterFactory.create())
                .build();
    }

    @Bean
    public PriceHttpApiService prideHttpApiService() {
        Retrofit retrofit = retrofit();
        return retrofit.create(PriceHttpApiService.class);
    }

}
