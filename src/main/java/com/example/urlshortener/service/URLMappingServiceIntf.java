package com.example.urlshortener.service;

import com.example.urlshortener.model.UrlMapping;

public interface URLMappingServiceIntf {
    String shortenUrl(String originalUrl);
    UrlMapping saveMapping(String originalUrl, String shortenedUrl);
    String getOriginalUrl(String shortenedUrl);

}
