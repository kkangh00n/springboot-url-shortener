package com.example.demo.domain.url;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UrlInfo {

    @Id
    private Long id;

    private String originalUrl;

    private String shortenUrl;

    private int request;

    public UrlInfo(Long id, String originalUrl, String shortenUrl){
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortenUrl = shortenUrl;
        this.request = 0;
    }

    public void addRequest(){
        this.request++;
    }

}
