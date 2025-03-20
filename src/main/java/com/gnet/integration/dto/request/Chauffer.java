package com.gnet.integration.dto.request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chauffer {
    private String griddID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Phone phones;
    private String chaufferId;
    private String picURL;
}
