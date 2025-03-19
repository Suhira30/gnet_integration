package com.gnet.integration.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationLite {
    private double lat;
    private double lng;

    public LocationLite() {
    }

    public LocationLite(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
