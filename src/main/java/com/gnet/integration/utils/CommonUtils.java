package com.gnet.integration.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpHead;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Slf4j
@Lazy
@Scope("prototype")
public class CommonUtils {

//    private static final Object COORDINATE_API_URL = "https://staging-mig.smartzi.com/smartzi-util/api/get/coordinate";
//    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private CommonUtils() {
    }

    public static String toJSON(Object payload) {
        String jsonResponse = null;
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            jsonResponse = objectWriter.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException occurred while parsing json in toJSON ", e);
        }
        return jsonResponse;
    }

    public static String getJson(Object payload) {
        String jsonResponse = null;
        ObjectWriter objectWriter = new ObjectMapper().writer();
        try {
            jsonResponse = objectWriter.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException occurred while parsing json in getJson", e);
        }
        return jsonResponse;
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
//
//    public static String getCoordinate(int mappingSource, int coordinateBy, String address, String postcode, String placeId) {
//        String requestBody = String.format(
//                "{\"mappingSourceNo\": %d, \"coordinateBy\": %d, \"address\": \"%s\", \"postcode\": \"%s\", \"placeId\": \"%s\"}",
//                mappingSource, coordinateBy, address, postcode, placeId
//        );
//        log.info("Request body: {}", requestBody);
////
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_JSON);
////
////        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
//
//        String jsonResponse = REST_TEMPLATE.postForObject((String) COORDINATE_API_URL, requestBody, String.class);
//        log.info("Coordinate API response: {}", jsonResponse);
//
////        String latitude = extractValue(jsonResponse, "latitude");
////        String longitude = extractValue(jsonResponse, "longitude");
//        return  jsonResponse;
//    }

    public static String getCoordinate(){
        return "51.22|-0.89";
    }
}
