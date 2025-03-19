package com.gnet.integration.service;

import com.gnet.integration.dto.request.GnetReceiveBookingRequest;
import com.gnet.integration.dto.request.response.GnetReceiveReservationResponse;

public interface GnetEngineServices {

    /**
     * receiveReservation
     * @param gnetReceiveBookingRequest
     * @return
     */
    public GnetReceiveReservationResponse receiveReservation(GnetReceiveBookingRequest gnetReceiveBookingRequest);
}
