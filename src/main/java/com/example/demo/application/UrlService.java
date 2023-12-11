package com.example.demo.application;

import com.example.demo.domain.url.UrlInfo;
import com.example.demo.domain.url.UrlRepository;
import com.example.demo.dto.Mapper;
import com.example.demo.dto.ResponseDto;
import com.example.demo.infrastructure.id.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UrlService {

    private final UrlRepository urlRepository;
    private final Encoder encoder;
    private final IdGenerator idGenerator;

    public ResponseDto shorteningUrl(String originalUrl){
        boolean exists = urlRepository.existsByOriginalUrl(originalUrl);
        if(exists){
            UrlInfo existsUrl = urlRepository.findUrlInfoByOriginalUrl(originalUrl);
            existsUrl.addRequest();
            return Mapper.of(existsUrl);
        }

        String shortenUrl = encoder.encoded(originalUrl);
        Id id = idGenerator.save();

        UrlInfo saveUrl = urlRepository.save(new UrlInfo(id.getId(), originalUrl, shortenUrl));
        return Mapper.of(saveUrl);
    }

    public String getOriginalUrl(String shortenUrl){
        UrlInfo urlInfo = urlRepository.findUrlInfoByShortenUrl(shortenUrl);
        return urlInfo.getOriginalUrl();
    }



}
