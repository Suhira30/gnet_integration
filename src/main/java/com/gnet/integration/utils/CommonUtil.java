package com.gnet.integration.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;


public class CommonUtil {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
