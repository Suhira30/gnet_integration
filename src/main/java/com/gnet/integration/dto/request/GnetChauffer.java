package com.gnet.integration.dto.request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GnetChauffer {
    private String griddID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private GnetPhone phones;
    private String chaufferId;
    private String picURL;
}
