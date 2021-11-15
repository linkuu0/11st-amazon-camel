package org.link.camel.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
