package com.example.urlshortener.service;

import com.example.urlshortener.model.UrlMapping;
import com.example.urlshortener.repository.URLMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class URLMappingServiceImpl implements URLMappingServiceIntf {
    private int getRandom() {
        return (int) (Math.random() * 9000000 + 1000000);
    }
    private final URLMappingRepository urlMappingRepository;

    @Autowired
    public URLMappingServiceImpl(final URLMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    @Override
    public String shortenUrl(final String originalUrl) {
        return String.valueOf(getRandom());
    }

    @Override
    public UrlMapping saveMapping(final String originalUrl, final String shortenedUrl) {
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setShortenedUrl(shortenedUrl);
        urlMapping.setOriginalUrl(originalUrl);
        return urlMappingRepository.save(urlMapping);
    }

    @Override
    public String getOriginalUrl(final String shortenedUrl) {
        final Optional<UrlMapping> originalURL = urlMappingRepository.findByShortenedUrl(shortenedUrl);
        return originalURL.isPresent() ? originalURL.get().getOriginalUrl() : null;
    }
}
