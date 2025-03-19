package com.gnet.integration.service.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gnet.integration.dao.GnetDao;
import com.gnet.integration.dto.request.bookingreq.GnetViewQuotesRequest;
import com.gnet.integration.dto.request.bookingreq.ValidVehicleGroupRequest;
import com.gnet.integration.dto.request.bookingreq.ValidVehicleRequest;
import com.gnet.integration.dto.request.bookingreq.response.GnetViewQuotesResponse;
import com.gnet.integration.entity.dynamo.B2BAccountDetail;
import com.gnet.integration.entity.dynamo.QuoteRequest;
import com.gnet.integration.exception.UnAuthorized;
import com.gnet.integration.repository.DynamoDBRepository;
import com.gnet.integration.service.GnetApplicationServices;
import com.gnet.integration.utils.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import smartzi.v2.vq.req.ViewQuoteV2Req;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Slf4j
@Service
@RequiredArgsConstructor
public class GnetApplicationServicesImpl implements GnetApplicationServices {

    @Value("${token.TableName.prefix}")
    private String prefixTableName;

    private final DynamoDBRepository dynamoDBRepository;

    GnetDao gnetDao;

//    private final DynamoDbEnhancedClient enhancedClient;

    public GnetViewQuotesResponse gnetGetViewQuotes(GnetViewQuotesRequest gnetViewQuotesRequest, String accessToken) {
        Long startTime = System.currentTimeMillis();
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        B2BAccountDetail b2BAccountDetail = null;
        String B2BAccountDetailId = null;
        try {
            /**
             * STEP 1 :token validity & b2bAccountDetailId fetching from DyT : stg-b2b-AccountDetail
             */

            String accountDetailTableName = prefixTableName + "AccountDetail";
            log.info("getB2BAccountInfo target table name {}", accountDetailTableName);

            b2BAccountDetail = (B2BAccountDetail) dynamoDBRepository.load(
                    B2BAccountDetail.class,
                    accessToken,
                    null,
                    accountDetailTableName
            );
            if (b2BAccountDetail == null) {
                log.error("No account found for access token: {}", accessToken);
                throw new UnAuthorized("Invalid or expired access token", "No matching account found for access token");
            }
            if (!accessToken.equals(b2BAccountDetail.getToB2BAccessToken())) {
                log.error("Access token mismatched for access token: {}", accessToken);
                throw new UnAuthorized("Invalid access token", "Access token mismatched for access token");
            }
            String tokenExpireyTime = b2BAccountDetail.getTokenDateTime();
            if (tokenExpireyTime == null) {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                if (LocalDate.now().isAfter(LocalDate.parse(tokenExpireyTime, formatter))) {
                    log.error("Access token expired: {}", tokenExpireyTime);
                    throw new UnAuthorized("Invalid access token", "Access token expired");
                }
            } else {
                log.warn("No expiry time set for token: {}", accessToken);
            }
            B2BAccountDetailId = String.valueOf(b2BAccountDetail.getB2BAccountDetailId());
            if (B2BAccountDetailId == null || B2BAccountDetailId.isEmpty()) {
                log.error("Invalid accountId: null, empty for b2BAccountDetail");
                throw new IllegalArgumentException("Account ID is null or empty");
            }
            log.info("B2BAccountDetailId is {} for valid token{}", B2BAccountDetailId, accessToken);


            /**
             * STEP 2 : Dumb viewQuotes request from GNET in to DyT : stg-b2b-StdQuoteRequest
             */
            if (gnetViewQuotesRequest != null) {
                String quoteRequestTableName = prefixTableName + "QuoteRequest";
                log.info("getB2BQuoteRequest target table name {}", quoteRequestTableName);
                QuoteRequest quoteRequest = new QuoteRequest();
                quoteRequest.setQuoteRequestId(CommonUtil.getUUID());
                quoteRequest.setSupplierId(b2BAccountDetail.getSupplierId());
                quoteRequest.setB2bAccountDetailId(Long.parseLong(B2BAccountDetailId));
                String dumpVQRequest = objectWriter.writeValueAsString(gnetViewQuotesRequest);
                quoteRequest.setRequest(dumpVQRequest);
//            quoteRequest.setDistanceMiles();
                dynamoDBRepository.save(quoteRequest, quoteRequestTableName);
                log.info("Successfully saved quote request with ID: {} ", quoteRequest.getQuoteRequestId());

            } else {
                throw new UnAuthorized("VQ Request is empty", "VQ Request is empty");
            }
            /**
             * STEP 3 : Date & time formatting and casting to europe / london time zone (if needed )
             */
            SimpleDateFormat smartziDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat utcDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            TimeZone tzUTC = TimeZone.getTimeZone("UTC");
            utcDateFormat.setTimeZone(tzUTC);
            TimeZone tzEurope = null;
            String formattedTravelDateTime = null;

            ViewQuoteV2Req viewQuoteV2Req = new ViewQuoteV2Req();
// In GNet timezone not given...
            if (gnetViewQuotesRequest.getLocations().getPickupTime() != null && !gnetViewQuotesRequest.getLocations().getPickupTime().equals("")) {
                tzEurope = TimeZone.getTimeZone(gnetViewQuotesRequest.getLocations().getPickupTime());
                viewQuoteV2Req.setSourceTimeZone(gnetViewQuotesRequest.getLocations().getPickupTime());
            } else {
                tzEurope = TimeZone.getTimeZone("Europe/London");
                viewQuoteV2Req.setSourceTimeZone("Europe/London");
            }
            smartziDateFormat.setTimeZone(tzEurope);

            if (gnetViewQuotesRequest.getLocations().getPickupTime() != null && !gnetViewQuotesRequest.getLocations().getPickupTime().equals("")) {
                smartziDateFormat.format(utcDateFormat.parse(gnetViewQuotesRequest.getLocations().getPickupTime()));
                log.info("time-zone---------------------->{}", tzEurope);
                log.info("GNet-VQ-formattedTravelDateTime---------->" + formattedTravelDateTime);
            } else {
                Date now = new Date();
                TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
                formattedTravelDateTime = smartziDateFormat.format(now);
                log.info("GNet-VQ-current-formattedTravelDateTime----------->" + formattedTravelDateTime);
            }
            viewQuoteV2Req.setTravelDateTime(formattedTravelDateTime);
            /**
             * STEP 4 : get the valid vehicle group for gent request using SP : publicdy.get_valid_vehicle_groups_for_b2b_vehicle_types_v10
             */
            ValidVehicleRequest validvehicleRequest = new ValidVehicleRequest();
            validvehicleRequest.setB2bVehicleType(gnetViewQuotesRequest.getPreferredVehicleType());
            validvehicleRequest.setTravelDateTime(gnetViewQuotesRequest.getLocations().getPickupTime());
            validvehicleRequest.setSourceTimeZone(viewQuoteV2Req.getSourceTimeZone());

            if(gnetViewQuotesRequest.getLocations() != null && gnetViewQuotesRequest.getLocations().getPickup() !=null && gnetViewQuotesRequest.getLocations().getPickup().getLon()!=0&&gnetViewQuotesRequest.getLocations().getPickup().getLat()!=0){
                validvehicleRequest.setSourceLat(gnetViewQuotesRequest.getLocations().getPickup().getLat());
                validvehicleRequest.setSourceLong(gnetViewQuotesRequest.getLocations().getPickup().getLon());
            }else{
                //---------------------need calculate lat and long for pickup---------------------
                validvehicleRequest.setSourceLat(0.0);
                validvehicleRequest.setSourceLong(0.0);
            }

            validvehicleRequest.setB2bAccountDetailId(Long.parseLong(B2BAccountDetailId));
            OfferVehicleB2BVTMapRes b2bVgList = gnetDao.getValidVehicleGroups(validvehicleRequest);

            /**
             * STEP 5 : if distance available in Gnet VQ request don't apply smartzi distance calculation utility
             * anywhere else apply smartzi distance utility for calculating : distance, ETA, routePointGEOJSON
             */

            /**
             * STEP 6 : concatenate comments and other required action parameters to store in smartzi VQ remarks
             */

            /**
             * STEP 7 : If flight number available in GENT Request call Flight Stats API : (IF REQUIRED ONLY)
             */

            /**
             * STEP 8 : Build other important VQ request parameter
             */

            /**
             * STEP 9 : Invoke Smartzi VQ API in b2bCore microservices and get the offer
             */

            /**
             * STEP 10 : Meantime while looping the offer dumb the VQ offers in DyT : stg-b2b-StdRequestOffer
             */

            /**
             * finale return of offer the as request by Gnet
             */

        } catch (UnAuthorized e) {
            log.error("Unauthorized access: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.info("An-exception occurred while processing replication engine view quotes : {}", e.getMessage());
        }
        return null;
    }
}