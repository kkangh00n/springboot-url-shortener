package com.example.demo.domain.url;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlInfo, Long> {

    boolean existsByOriginalUrl(String originalUrl);

    Optional<UrlInfo> findUrlInfoByOriginalUrl(String originalUrl);

    Optional<UrlInfo> findUrlInfoByShortenUrl(String shortenUrl);

}
