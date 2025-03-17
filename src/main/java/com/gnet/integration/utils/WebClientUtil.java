package com.gnet.integration.utils;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientUtil {

    public static WebClient createWebClient() {
        return WebClient.builder()
                .build();
    }

    public static RestTemplate  restTemplate() {
        return new RestTemplate();
    }
}
