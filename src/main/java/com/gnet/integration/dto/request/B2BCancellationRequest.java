package com.gnet.integration.dto.request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class B2BCancellationRequest {
    private long tripReservationId;
    private short tripReservationSubStatus;
    private long tripCancelReasonId;
    private String cancelReason;
    private short cancelSource;
    private boolean isCardPaymentReleased;
    private Long[] cancelProofImageIds;
    private double currentLat;
    private double currentLong;
    private long userId;
public B2BCancellationRequest(GnetReceiveBookingRequest gnetReceiveBookingRequest) {
 this.tripReservationId = Long.parseLong(gnetReceiveBookingRequest.getTransactionId());
 this.tripReservationSubStatus=0;
 this.tripCancelReasonId=0;
 this.cancelReason="";
 this.cancelSource=0;
 this.isCardPaymentReleased=false;
 this.cancelProofImageIds=null;
 this.currentLat=0;
 this.cancelReason="";
 this.currentLong=0;
 this.userId=0;
}

}
