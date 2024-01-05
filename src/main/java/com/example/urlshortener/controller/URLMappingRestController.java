package com.example.urlshortener.controller;

import com.example.urlshortener.service.URLMappingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/url")
public class URLMappingRestController {

    @GetMapping("/test")
    public String test() {
        return "test worked!";
    }

    private static final String BASE_URL = "http://localhost:9000/api/url/";

    private final URLMappingServiceImpl urlMappingService;

    @Autowired
    public URLMappingRestController(URLMappingServiceImpl urlMappingService) {
        this.urlMappingService = urlMappingService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestParam String originalUrl) {
        String shortenedUrl = urlMappingService.shortenUrl(originalUrl);
        urlMappingService.saveMapping(originalUrl, shortenedUrl);
        shortenedUrl = BASE_URL + shortenedUrl;
        return new ResponseEntity<>(shortenedUrl, HttpStatus.CREATED);
    }

    @GetMapping("/{shortenedUrl}")
    public ResponseEntity<String> redirectToOriginalUrl(@PathVariable String shortenedUrl) {
        String originalUrl = urlMappingService.getOriginalUrl(shortenedUrl);
        if (originalUrl != null) {
            return new ResponseEntity<>(originalUrl, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>("Shortened URL not found", HttpStatus.NOT_FOUND);
        }
    }
}
