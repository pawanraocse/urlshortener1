package com.example.urlshortener;

import com.example.urlshortener.controller.URLMappingRestController;
import com.example.urlshortener.service.URLMappingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(URLMappingRestController.class)
class UrlControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean // Use MockBean annotation to mock the service
    private URLMappingServiceImpl urlMappingService;

    @Test
    void testShortenUrlEndpoint() {
        String originalUrl = "https://www.example.com";
        String shortenedUrl = "abc123";
        when(urlMappingService.shortenUrl(originalUrl)).thenReturn(shortenedUrl);
        try {
            mockMvc.perform(post("http://localhost:9000/api/url/shorten")
                    .param("originalUrl", originalUrl)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(endsWith(shortenedUrl)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
