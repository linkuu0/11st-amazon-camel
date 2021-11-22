package org.link.camel.web.controller;

import lombok.RequiredArgsConstructor;
import org.link.camel.service.PriceService;
import org.link.camel.web.PriceSaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("price")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping("{productId}")
    public ResponseEntity<PriceSaveRequest> productInfo(@PathVariable Long productId) {
        return ResponseEntity.ok().body(priceService.getPriceInfo(productId));
    }

}
