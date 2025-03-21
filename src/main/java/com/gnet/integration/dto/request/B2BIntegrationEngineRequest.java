package com.gnet.integration.dto.request;

import com.gnet.integration.dto.request.GnetReceiveBookingRequest;
import com.gnet.integration.dto.request.LocationEx;
import com.gnet.integration.dto.request.LocationLite;
import com.gnet.integration.dto.request.RouteStop;
import com.gnet.integration.utils.CommonUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class B2BIntegrationEngineRequest {
    private boolean VIPBooking;
    private double distance;
    private boolean distanceExist;
    private String flightActualArrivalDatetime;
    private String referenceNo;
    private long tripRequestId;
    private long tripRequestOfferId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerMobileNo;
    private double customEstimatedFare;
    private boolean applyCustomEstimatedFare;
    private String adminNotes;
    private long b2bAccountDetailId;
    private String extReferenceNo;
    private boolean immediateBooking;
    private long agentDetailId;
    private List<String> vehicleTypeArray;
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
    private boolean isSharedBooking;

    private String testStr;

    public B2BIntegrationEngineRequest(GnetReceiveBookingRequest gnetReceiveBookingRequest,
                                       long b2bAccountDetailId,
                                       String extReferenceNo,
                                       LocationLite pickUpCoordinates,
                                       LocationLite dropOffCoordinates) {
        String[] coordinates = CommonUtils.getCoordinate().split("|");
        this.VIPBooking = false;
        this.distance  = 0.0;
        this.distanceExist = false;
//        this.flightActualArrivalDatetime : call flight stats
        this.referenceNo = "";
        this.tripRequestId = 0;
        this.tripRequestOfferId = 0;
        this.customerFirstName = gnetReceiveBookingRequest.getPassengers().get(0).getFirstName();
        this.customerLastName = gnetReceiveBookingRequest.getPassengers().get(0).getLastName();
        this.customerEmail = gnetReceiveBookingRequest.getPassengers().get(0).getEmail();
        this.customEstimatedFare = 0.0;
        this.applyCustomEstimatedFare = false;
        this.adminNotes = "";
        this.b2bAccountDetailId = b2bAccountDetailId;
        this.extReferenceNo = extReferenceNo;
        this.immediateBooking = false;
        this.agentDetailId = -1;
        this.vehicleTypeArray = Collections.singletonList(gnetReceiveBookingRequest.getPreferredVehicleType());
        this.currentLocation = new LocationLite(0.0, 0.0);
        this.sourceLocation = new LocationEx(
                pickUpCoordinates.getLat(),
                pickUpCoordinates.getLng(),
                gnetReceiveBookingRequest.getLocations().getPickup().getAdress(),
                gnetReceiveBookingRequest.getLocations().getPickup().getZipCode()
                );
        this.destinationLocation = new LocationEx(
                dropOffCoordinates.getLat(),
                dropOffCoordinates.getLng(),
                gnetReceiveBookingRequest.getLocations().getDropOff().getAdress(),
                gnetReceiveBookingRequest.getLocations().getDropOff().getZipCode()
        );
        this.travelDateTime=gnetReceiveBookingRequest.getLocations().getPickup().getTime().toString();
        this.sourceTimeZone="europe/london";
        this.customerId=0;
        this.flightNumber=gnetReceiveBookingRequest.getLocations().getPickup().getFlightInfo().getFlightNumber();
        this.flightDateTime="";
        this.passengerCount= Short.parseShort(gnetReceiveBookingRequest.getPassengerCount());
        this.luggageCount=0;
        this.remarks=
                "PickUp LandMark: " +
                gnetReceiveBookingRequest.getLocations().getPickup().getLandmark() +
                "PickUp special Instruction : " +
                gnetReceiveBookingRequest.getLocations().getPickup().getSpecialInstructions() +
                        "DropOff LandMark : " + gnetReceiveBookingRequest.getLocations().getDropOff().getLandmark() +
                        "DropOff special Instruction : " + gnetReceiveBookingRequest.getLocations().getDropOff().getSpecialInstructions()

        ;
        this.testStr = CommonUtils.getCoordinate();
    }
}
