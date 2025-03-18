//package com.gnet.integration.service.impl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.gnet.integration.dto.request.bookingreq.GnetViewQuotesRequest;
//import com.gnet.integration.dto.request.bookingreq.response.GnetViewQuotesResponse;
//import com.gnet.integration.service.GnetEngineServices;
//import com.google.gson.Gson;
//import org.springframework.stereotype.Service;
//import smartzi.dto.rest.webservices.smartzi.util.response.DirectionAPIMapRes;
//
//@Service
//public class GnetServicesImpl implements GnetEngineServices {
//    public GnetViewQuotesResponse processReplicationEngineViewQuotesRequest(GnetViewQuotesRequest replicationEngineViewQuotesRequest, String accessToken) {
//        Long startTime = System.currentTimeMillis();
//        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        GnetViewQuotesResponse bookingComViewQuotesResponse = new GnetViewQuotesResponse();
//        Gson gson = new Gson();
//        DirectionAPIMapRes directionAPIMapRes = null;
//        long b2bAccountDetailId = 0L;
//        String redisTableName = "SupplierDataEx";
//        try {
//            DumpReplicationVQRequest dumpvqrequest = null;
////            B2BAccountDetailEntity tokenEntity = replicationEngineRepository.getOauthToken(accessToken);
//            Object authObj =  redisService.getValue(redisTableName,"BDC");
//            log.info("processReplicationEngineViewQuotesRequest-authObj------------->{}", gson.toJson(authObj));
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            B2BAccountDetailEntity tokenEntity = objectMapper.readValue(authObj.toString(), B2BAccountDetailEntity.class);
//            log.info("processReplicationEngineViewQuotesRequest-tokenEntity------------->{}", gson.toJson(tokenEntity));
//
//            LogInfo logInfo = null;
//            final QuoteReqResEntity entity;
//
//            if (tokenEntity != null) {
//                dumpvqrequest = new DumpReplicationVQRequest();
//
//                dumpvqrequest.setApi("/v2/search-results");
//                dumpvqrequest.setSupplierId(tokenEntity.getSupplierId());
//                dumpvqrequest.setRequest(replicationEngineViewQuotesRequest);
//
//                b2bAccountDetailId = tokenEntity.getB2BAccountDetailId();
//            } else {
//                logInfo = new LogInfo();
//                logInfo.setApi("/v2/search-results");
//                logInfo.setRequest(objectWriter.writeValueAsString(replicationEngineViewQuotesRequest));
//                logInfo.setResponse("access token invalid");
//                logInfo.setSupplierId("");
//                logInfo.setTripReservationId(0);
//                logInfo.setExtRefNo("");
//                String logid = replicationEngineRepository.insertValidationLogs(logInfo);
//                log.info("logid-----------{}", logid);
//                throw new UnAuthorized("Invalid Authorization Token", "Invalid authorization token in header.");
//            }
//}
