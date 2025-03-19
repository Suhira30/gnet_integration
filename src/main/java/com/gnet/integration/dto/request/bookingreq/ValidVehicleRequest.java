package com.gnet.integration.dto.request.bookingreq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidVehicleRequest {

    private String travelDateTime;
    private String sourceTimeZone;
    private double sourceLat;
    private double sourceLong;
    private long sourceLocationId;
    private long b2bAccountDetailId;
    private String b2bVehicleType;
}
