package com.example.urlshortener;

import com.example.urlshortener.model.UrlMapping;
import com.example.urlshortener.repository.URLMappingRepository;
import com.example.urlshortener.service.URLMappingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UrlMappingServiceTest {

    @InjectMocks
    private URLMappingServiceImpl urlMappingService;

    @Mock
    private URLMappingRepository urlMappingRepository;

    @Test
    public void testShortenUrl() {
        String originalUrl = "https://www.example.com";
        String shortenedUrl = urlMappingService.shortenUrl(originalUrl);
        assertNotNull(shortenedUrl);
    }
    @Test
    public void testSaveMapping() {
        // Given
        String originalUrl = "https://www.example.com";
        String shortenedUrl = "ABC123"; // Replace with the expected shortened URL

        UrlMapping mockUrlMapping = new UrlMapping();
        mockUrlMapping.setId(1L); // Set an ID
        mockUrlMapping.setOriginalUrl(originalUrl);
        mockUrlMapping.setShortenedUrl(shortenedUrl);

        // Mock the behavior of the repository's save method to return the mockUrlMapping
        doReturn(mockUrlMapping).when(urlMappingRepository).save(any(UrlMapping.class));

        // When
        UrlMapping savedMapping = urlMappingService.saveMapping(originalUrl, shortenedUrl);

        // Then
        assertNotNull(savedMapping);
        assertEquals(originalUrl, savedMapping.getOriginalUrl());
        assertEquals(shortenedUrl, savedMapping.getShortenedUrl());
        // Add more assertions if needed
    }
}
