package com.gnet.integration.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteStop extends LocationEx{
    private int stopNo;
    private double distance;

    public RouteStop() {
    }

    public RouteStop(int stopNo, double distance) {
        this.stopNo = stopNo;
        this.distance = distance;
    }

    public RouteStop(String address, int stopNo, double distance) {
        super(address);
        this.stopNo = stopNo;
        this.distance = distance;
    }

    public RouteStop(double lat, double lng, String address, int stopNo, double distance) {
        super(lat, lng, address);
        this.stopNo = stopNo;
        this.distance = distance;
    }

    public RouteStop(double lat, double lng, String postcode, int stopNo) {
        super(lat, lng, postcode);
        this.stopNo = stopNo;
    }
}
