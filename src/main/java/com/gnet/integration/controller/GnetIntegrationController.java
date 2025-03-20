package com.gnet.integration.controller;

import com.gnet.integration.dto.request.response.GnetReceiveReservationResponse;
import com.gnet.integration.utils.CommonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

//    @PostMapping("/coordinates")
//    public String coordinates(@RequestBody int mappingSource, int coordinateBy, String address, String postcode, String placeId ){
//        return CommonUtils.getCoordinate(mappingSource,coordinateBy,address,postcode,placeId);
//    }
}
