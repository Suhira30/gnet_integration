package com.gnet.integration.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.util.UUID;

@Slf4j
@Lazy
@Scope("prototype")
public class CommonUtils {
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

    public static String getCoordinate(){
        return "51.22|-0.89";
    }
}
