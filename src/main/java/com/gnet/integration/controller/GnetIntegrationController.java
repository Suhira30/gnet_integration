package com.gnet.integration.controller;

import com.gnet.integration.dto.request.response.GnetReceiveReservationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GnetIntegrationController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @PostMapping("/test")
    public GnetReceiveReservationResponse receiveReservation(){
        return null;
    }

}
