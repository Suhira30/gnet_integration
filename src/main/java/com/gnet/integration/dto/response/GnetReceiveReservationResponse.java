package com.gnet.integration.dto.request.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GnetReceiveReservationResponse {
    private boolean success;
    private String message;
    private String totalAmount;
    private String transactionId;
}
