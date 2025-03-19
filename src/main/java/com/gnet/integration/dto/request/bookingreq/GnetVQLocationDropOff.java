package com.gnet.integration.dto.request.bookingreq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GnetVQLocationDropOff {
private String address1;
private String city;
private String state;
private String zipCode;
private double lat;
private double lon;
}
