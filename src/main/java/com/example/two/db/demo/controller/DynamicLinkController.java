package com.example.two.db.demo.controller;

import com.example.two.db.demo.service.DynamicLinkService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/dynamic-links")
public class DynamicLinkController {

    private final DynamicLinkService dynamicLinkService;

    public DynamicLinkController(DynamicLinkService dynamicLinkService) {
        this.dynamicLinkService = dynamicLinkService;
    }

    @GetMapping("/generate")
    public String generateDynamicLink(@RequestParam String longLink) throws IOException {
        return dynamicLinkService.generateDynamicLink(longLink);
    }
}

