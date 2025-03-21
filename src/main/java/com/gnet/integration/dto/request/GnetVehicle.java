package com.gnet.integration.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GnetVehicle {

    private String griddID;
    private String vehicleID;
    private String vin;
    private String passengerCount;
    private String make;
    private String model;
    private String year;
    private String mileage;
    private String active;
    private String plateNo;
    private String vColor;
}
