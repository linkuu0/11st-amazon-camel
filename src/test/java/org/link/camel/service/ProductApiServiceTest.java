package org.link.camel.service;

import com.linecorp.armeria.client.WebClient;
import com.linecorp.armeria.client.retrofit2.ArmeriaRetrofit;
import com.linecorp.armeria.common.AggregatedHttpResponse;
import com.linecorp.armeria.common.HttpHeaderNames;
import com.linecorp.armeria.common.HttpMethod;
import com.linecorp.armeria.common.RequestHeaders;
import org.junit.Test;
import org.link.camel.api.ProductHttpApiCode;
import org.link.camel.api.ProductInfoResponse;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

public class ProductApiServiceTest {

    String apiUrl = "http://openapi.11st.co.kr/";
    String apiKey = "db135628136ea74820b2890de13972ea";
    Long productId = 3545503877L;

    @Test
    public void getPriceInfoTest() throws ExecutionException, InterruptedException {
        Retrofit retrofit = ArmeriaRetrofit.builder(apiUrl)
                .addConverterFactory(JaxbConverterFactory.create())
                .build();

        ProductHttpApiService service = retrofit.create(ProductHttpApiService.class);
        ProductInfoResponse response = service.getProductInfo(apiKey, ProductHttpApiCode.PRODUCT_INFO.getCode(), productId).get();

        System.out.println("response = " + response);

    }

    @Test
    public void getProductInfoWithWebClientTest() {
        WebClient client = WebClient.of(apiUrl);
        RequestHeaders getJson = RequestHeaders.of(HttpMethod.GET, "/openapi/OpenApiService.tmall?key=db135628136ea74820b2890de13972ea&apiCode=ProductInfo&productCode=1000",
                HttpHeaderNames.ACCEPT, "text/xml");
        AggregatedHttpResponse join = client.execute(getJson).aggregate().join();
        System.out.println("join = " + join.content(Charset.forName("euc-kr")));

        String content = client.get("/openapi/OpenApiService.tmall?key=db135628136ea74820b2890de13972ea&apiCode=ProductInfo&productCode=1000").aggregate().join().content(Charset.forName("euc-kr"));
        System.out.println("content = " + content);

        UriComponents build = UriComponentsBuilder.newInstance()
                .path("/openapi/OpenApiService.tmall")
                .queryParam("key", apiKey)
                .queryParam("apiCode", ProductHttpApiCode.PRODUCT_INFO.getCode())
                .queryParam("productCode", productId)
                .build(false);

        String s = build.toString();
        System.out.println("s = " + s);


    }

}
