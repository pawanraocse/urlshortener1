package com.example.urlshortener.repository;

import com.example.urlshortener.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface URLMappingRepository extends JpaRepository<UrlMapping, Long> {
    Optional<UrlMapping> findByShortenedUrl(String shortenedUrl);
}
