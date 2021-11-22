package org.link.camel.web.controller;

import lombok.RequiredArgsConstructor;
import org.link.camel.service.ItemService;
import org.link.camel.web.ItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("{productId}")
    public ResponseEntity<ItemResponse> itemInfoMain(@PathVariable Long productId) throws ExecutionException, InterruptedException, JAXBException {
        ItemResponse response = itemService.getItemInfo(productId);
        return ResponseEntity.ok().body(response);
    }

}
