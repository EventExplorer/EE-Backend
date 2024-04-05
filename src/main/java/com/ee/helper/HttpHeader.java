package com.ee.helper;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.time.Duration;

public class HttpHeader {

    public static HttpHeaders httpHeaders() {

        HttpHeaders headers =   new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setCacheControl(CacheControl.maxAge(Duration.ZERO));
        headers.add("x-content-type-options","nosniff");

        return headers;
    }
}
