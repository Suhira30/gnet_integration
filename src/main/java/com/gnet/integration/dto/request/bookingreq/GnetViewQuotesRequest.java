package com.gnet.integration.dto.request.bookingreq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GnetViewQuotesRequest {
    private String reservationDate;
    private AffiliateReservation affiliateReservation;
    private String reservationType;
    private String status;
    private String runType;
    private short passengerCount;
    private String totalTripDuration;
    private String eventName;
    private List<Passenger> passengers;
    private String namesignInstructions;
    private GnetVQLocation locations;
    private String specialInstructions;
    private String sourceVendor;
    private String origination;
    private List<String> note;
    private String preferredVehicleType;
    private String submitMode;
}
