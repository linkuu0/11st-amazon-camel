package org.link.camel.web.controller;

import lombok.RequiredArgsConstructor;
import org.link.camel.service.ItemService;
import org.link.camel.web.ItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("item")
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("{productId}")
    public ResponseEntity<ItemResponse> productInfoMain(Long productId) {
        ItemResponse response = itemService.getProductInfo(productId);
        return ResponseEntity.ok().body(response);
    }

}
