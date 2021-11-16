package org.link.camel.service;

import com.linecorp.armeria.client.retrofit2.ArmeriaRetrofit;
import org.junit.Test;
import org.link.camel.config.PriceHttpApiCode;
import org.link.camel.config.PriceHttpApiService;
import org.link.camel.config.ProductInfoResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import java.util.concurrent.ExecutionException;

public class PriceApiServiceTest {

    String apiUrl = "http://openapi.11st.co.kr/";
    String apiKey = "db135628136ea74820b2890de13972ea";
    Long productId = 3545503877L;

    @Test
    public void getPriceInfoTest() throws ExecutionException, InterruptedException {
        Retrofit retrofit = ArmeriaRetrofit.builder(apiUrl)
                .addConverterFactory(JaxbConverterFactory.create())
                .build();

        PriceHttpApiService service = retrofit.create(PriceHttpApiService.class);
        ProductInfoResponse response = service.getPriceInfo(apiKey, PriceHttpApiCode.PRODUCT_INFO.getCode(), productId).get();

        System.out.println("response = " + response);

    }

}
