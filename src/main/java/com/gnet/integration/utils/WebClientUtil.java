package com.gnet.integration.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Slf4j
@Lazy
@Scope("prototype")
@RequiredArgsConstructor
public class WebClientUtil {
    private final WebClient webClient;

    public String webClientPost(String requestURL, Object object, boolean authRequired) {
        String response = "";
        String token = "";

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            log.info("webClientPost-request-url : {}", requestURL);

            try {
                if (authRequired) {
                    log.info("webClientPost-with-authentication-required");

                    response = webClient.post()
                            .uri(requestURL)
                            .header(HttpHeaders.CONTENT_TYPE, "application/json")
                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                            .body(Mono.just(object), Object.class)
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();
                } else {
                    log.info("webClientPost-without-authentication-required");

                    response = webClient.post()
                            .uri(requestURL)
                            .header(HttpHeaders.CONTENT_TYPE, "application/json")
                            .body(Mono.just(object), Object.class)
                            .retrieve()
                            .bodyToMono(String.class).
                            block();
                }
            } catch (WebClientResponseException e) {
                log.info("A-WebClientResponseException-occurred-in-webClientPost : {}", e.toString());
            }
        } catch (Exception e) {
            log.info("Exception-http-client-exception : {}", e.getMessage());
        }
        return response;
    }
}
