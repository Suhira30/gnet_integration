package com.gnet.integration.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Passenger {
private String firstName;
private String lastName;
private String email;
private String phoneNumber;
private Phone phones;
}
