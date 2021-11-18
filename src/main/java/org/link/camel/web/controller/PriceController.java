package org.link.camel.web.controller;

import lombok.RequiredArgsConstructor;
import org.link.camel.service.PriceService;
import org.link.camel.web.PriceSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("price")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    /**
     * 11st OpenAPI로 얻은 제품 정보
     * TODO JSON 변환 필요함
     * @param productId
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("{productId}")
    public ResponseEntity<PriceSaveRequest> productInfo(@PathVariable Long productId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok().body(priceService.getLowestPriceInfo(productId));
    }

}
