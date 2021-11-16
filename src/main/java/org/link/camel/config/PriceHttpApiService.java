package org.link.camel.config;

import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.concurrent.CompletableFuture;

/**
 * ErrorResponse 처리 확인 필요
 */
public interface PriceHttpApiService {
    
    @GET("/openapi/OpenApiService.tmall")
    CompletableFuture<ProductInfoResponse> getPriceInfo(@Query("key") String key,
                                                        @Query("apiCode") String apiCode,
                                                        @Query("productCode") Long productId);

}
