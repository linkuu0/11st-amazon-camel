package org.link.camel.service;

import org.link.camel.api.ProductInfoResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.concurrent.CompletableFuture;

/**
 * ErrorResponse 처리 확인 필요
 */
@Deprecated
public interface ProductHttpApiService {
    
    @GET("/openapi/OpenApiService.tmall")
    CompletableFuture<ProductInfoResponse> getProductInfo(@Query("key") String key,
                                                          @Query("apiCode") String apiCode,
                                                          @Query("productCode") Long productId);

}
