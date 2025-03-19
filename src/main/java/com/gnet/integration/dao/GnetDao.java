package com.gnet.integration.dao;

import com.gnet.integration.dto.request.bookingreq.ValidVehicleRequest;
import com.smartzi.booking_com_integration.dto.request.B2BDetailFromAppIdRequest;
import com.smartzi.booking_com_integration.dto.request.BdcViewQuoteRequest;
import com.smartzi.booking_com_integration.dto.request.ValidVehicleGroupRequest;
import com.smartzi.booking_com_integration.dto.response.B2BDetailFromAppIdV2Response;
import com.smartzi.booking_com_integration.dto.response.OfferVehicleGroupB2BVTMapRes;
import smartzi.dto.util.common.GeneralSPResponse;
import smartzi.v2.b2b.req.GetB2BVehicleGroupMapReq;
import smartzi.v2.b2b.res.VehicleGroupB2BVTMapRes;

import java.util.List;

public interface GnetDao {


    /**
     * getValidVehicleGroups
     * @param validvehicleRequest
     * @return OfferVehicleB2BVTMapRes
     */
    VehicleGroupB2BVTMapRes getValidVehicleGroups(ValidVehicleRequest validvehicleRequest);


}
