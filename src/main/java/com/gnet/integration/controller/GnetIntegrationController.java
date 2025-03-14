package com.gnet.integration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GnetIntegrationController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
