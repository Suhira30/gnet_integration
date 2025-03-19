package com.gnet.integration.dto.request.bookingreq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GnetVQLocationPickup {
    private String country;
    private FlightInfo flightInfo;
    private String meetAndGreet;
    private double lat;
    private double lon;

}
