package com.example.demo.domain.url;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlInfo, Long> {

    boolean existsByOriginalUrl(String originalUrl);

    UrlInfo findUrlInfoByOriginalUrl(String originalUrl);

    UrlInfo findUrlInfoByShortenUrl(String shortenUrl);

}
