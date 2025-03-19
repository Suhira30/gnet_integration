package com.gnet.integration.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationEx extends LocationLite{
    private String address;
    private String postcode;

    public LocationEx() {
    }

    public LocationEx(String address) {
        this.address = address;
    }

    public LocationEx(double lat, double lng, String address) {
        super(lat, lng);
        this.address = address;
    }

    public LocationEx(String address, String postcode) {
        this.address = address;
        this.postcode = postcode;
    }

    public LocationEx(double lat, double lng, String address, String postcode) {
        super(lat, lng);
        this.address = address;
        this.postcode = postcode;
    }
}
