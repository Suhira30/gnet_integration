package com.gnet.integration.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GnetReceiveBookingLocationLite extends GnetReceiveBookingPickup {
    private String locationType;
    private String FBO;
    private String landmark;
    private String adress1;
    private String adress2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String specialInstructions;
    private FlightInfo flightInfo;
}
