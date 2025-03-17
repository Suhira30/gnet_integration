package com.gnet.integration.dto.request.bookingreq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GnetVQLocation {
    private GnetVQLocationPickup pickup;
    private GnetVQLocationDropOff dropOff;
}
