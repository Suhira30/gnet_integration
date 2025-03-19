package com.gnet.integration.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GnetVQLocation {
    private GnetReceiveBookingLocationLite pickup;
    private GnetReceiveBookingLocationLite dropOff;
}
