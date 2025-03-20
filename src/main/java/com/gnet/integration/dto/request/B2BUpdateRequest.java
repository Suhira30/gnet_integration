package com.gnet.integration.dto.request;

import java.util.List;

public class B2BUpdateRequest {
        private LocationLite currentLocation;
        private LocationEx sourceLocation;
        private LocationEx destinationLocation;
        private String travelDateTime;
        private String sourceTimeZone;
        private long customerId;
        private String flightNumber;
        private String flightDateTime;
        private short passengerCount;
        private short luggageCount;
        private String remarks;
        private List<RouteStop> multiStopsAry;
        private long tripReservationId;
        private long tripRequestId;
        private long tripRequestOfferId;
        private long b2bAccountDetailId;
        private long vehicleTypeId;
        private double customEstimatedFare;
        private boolean applyCustomEstimatedFare;
        private String adminNotes;
        private String customerFirstName;
        private String customerLastName;
        private String customerEmail;
        private String customerMobileNo;
        private long agentDetailId;
        private List<String> vehicleTypeArray;
        private String flightActualArrivalDatetime;
        private boolean VIPBooking;
        private double distance;
        private boolean distanceExist;
        private boolean isEditPickUpDateTimeEnable = false;

        public B2BUpdateRequest() {

        }

}
