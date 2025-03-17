package com.gnet.integration.config;

import org.springframework.web.reactive.function.client.WebClient;

public class WebClientConfig {

    public static WebClient createWebClient(String baseUrl) {
        return WebClient.builder().build();

    }

}
