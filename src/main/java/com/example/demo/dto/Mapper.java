package com.example.demo.dto;

import com.example.demo.domain.url.UrlInfo;

public class Mapper {

    public static ResponseDto of(UrlInfo urlInfo){
        return new ResponseDto(urlInfo.getShortenUrl(), urlInfo.getRequest());
    }

}
