package com.gnet.integration.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AffiliateReservation {
    private String action;
    private String status;
    private String requesterId;
    private String requesterResNo;
    private String providerId;
    private String notes;
}
