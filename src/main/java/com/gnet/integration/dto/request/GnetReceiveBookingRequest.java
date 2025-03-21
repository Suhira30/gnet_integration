package com.gnet.integration.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GnetReceiveBookingRequest  {
    private String reservationDate;
    private GnetAffiliateReservation affiliateReservation;
    private String reservationType;
    private String runType;
    private String passengerCount;
    private String totalTripDuration;
    private List<GnetPassenger> passengers;
    private String namesignInstructions;
    private GnetVQLocation locations;
    private String specialInstructions;
    private GnetNote notes;
    private String preferredVehicleType;
    private String submitMode;
    private GnetAccount account;
    private String transactionId;
    private GnetVehicle vehicle;
    private String status;
    private String totalAmount;
    private List<GnetFees> fees;
    private GnetChauffer chauffer;
//    private String sourceVendor;
//    private String origination;
}
