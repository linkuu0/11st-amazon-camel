package org.link.camel.web.controller;

import lombok.RequiredArgsConstructor;
import org.link.camel.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("items")
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("{itemId}")
    public ResponseEntity<Long> itemInfo(Long itemId) {
        return ResponseEntity.ok().body(itemId);
    }

}
