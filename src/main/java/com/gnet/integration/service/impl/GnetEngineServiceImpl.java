package com.gnet.integration.service.impl;

import com.gnet.integration.dto.request.B2BCancellationRequest;
import com.gnet.integration.dto.request.B2BIntegrationEngineRequest;
import com.gnet.integration.dto.request.GnetReceiveBookingRequest;
import com.gnet.integration.dto.request.response.GnetReceiveReservationResponse;
import com.gnet.integration.repository.dynamo.DynamoDBRepository;
import com.gnet.integration.repository.dynamo.Repos.QuoteRequest;
import com.gnet.integration.service.GnetEngineServices;
import com.gnet.integration.utils.CommonUtils;
import com.gnet.integration.utils.DateUtil;
import com.gnet.integration.utils.WebClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class GnetEngineServiceImpl implements GnetEngineServices {

    @Value("${smartzi.core.service.url}")
    private String coreUrl;

    @Value("${smartzi.core.cancel.url}")
    private String coreCancelUrl;

    private final DynamoDBRepository dynamoDBRepository;

    GnetReceiveReservationResponse gnetReceiveReservationResponse = new GnetReceiveReservationResponse();

    @Override
    public GnetReceiveReservationResponse receiveReservation(GnetReceiveBookingRequest gnetReceiveBookingRequest) {
        try {
            if (gnetReceiveBookingRequest.getAffiliateReservation() != null) {
                /**
                 * STEP 1 : Dump the request
                 */
                QuoteRequest quoteRequest = new QuoteRequest();
                quoteRequest.setQuoteRequestId(CommonUtils.getUUID());
                quoteRequest.setSupplierId(supplierId);

                quoteRequest.setB2bAccountDetailId(b2bAccountDetailId);
                quoteRequest.setTripRequestId(b2BIntViewQuoteV3Res.getTripRequestId());

                quoteRequest.setPayload(CommonUtils.toJSON(gnetReceiveBookingRequest));
//                quoteRequest.setCorePayload(CommonUtils.toJSON(b2BIntViewQuotesV2Req));
//                quoteRequest.setResponse(CommonUtils.toJSON(generalAPIResponse));

                quoteRequest.setCreatedOnUTCUnix(DateUtil.getCurrentUTCUNIXTime());
                quoteRequest.setCreatedOnUTCUnixDate(DateUtil.getCurrentDateUNIXTime());
                dynamoDBRepository.saveV2(quoteRequest, "");

                if (!gnetReceiveBookingRequest.getAffiliateReservation().getAction().isEmpty() &&
                        gnetReceiveBookingRequest.getAffiliateReservation().getStatus().equals("NEW")) {
                    /**
                     * STEP 2 : call API : /leap-communication/public/combook/get/b2b/integration/booking
                     */
                    B2BIntegrationEngineRequest b2BIntegrationEngineRequest = new B2BIntegrationEngineRequest(gnetReceiveBookingRequest);

                } else if (gnetReceiveBookingRequest.getAffiliateReservation().getAction().equals("UPDATE") &&
                        gnetReceiveBookingRequest.getAffiliateReservation().getStatus().isEmpty()) {
                    B2BCancellationRequest updateRequest = new B2BCancellationRequest(gnetReceiveBookingRequest);

                } else if (gnetReceiveBookingRequest.getAffiliateReservation().getAction().equals("CANCEL") &&
                        !gnetReceiveBookingRequest.getAffiliateReservation().getStatus().isEmpty()) {
                    B2BCancellationRequest cancellationRequest = new B2BCancellationRequest(gnetReceiveBookingRequest);
                    try {
                        log.info("CancelBooking-Request----------->" + CommonUtils.toJSON(cancellationRequest));
                        log.info("cancelBooking-baseUrl-requestUrl------>" + coreUrl + coreCancelUrl);
//                        RestTemplate restTemplate = new RestTemplate();
//                        HttpHeaders headers = new HttpHeaders();
//                        headers.setContentType(MediaType.APPLICATION_JSON);
//                        HttpEntity<Object> request = new HttpEntity<>(cancellationRequest, headers);
//                        String response = restTemplate.postForEntity(coreUrl + coreCancelUrl, request, String.class).getBody();

                        WebClientUtil webClientUtil = new WebClientUtil(WebClient.create());
                        String response = webClientUtil.webClientPost( coreUrl + coreCancelUrl, cancellationRequest, false);
                        log.info("cancelBooking-response------->" + response);
                    } catch (Exception e) {
                        log.error("Exception occur while cancelling booking" + e.getMessage());
                    }
                }

            } else {
                gnetReceiveReservationResponse.setSuccess(Boolean.FALSE);
                gnetReceiveReservationResponse.setMessage("Affiliate reservation failed");
                gnetReceiveReservationResponse.setTotalAmount("0.00");
                gnetReceiveReservationResponse.setTransactionId(null);
            }
        } catch (Exception e) {
            log.info("An error occurred while receiving reservation : {}", e.getMessage());
        }
        return new GnetReceiveReservationResponse();
    }
}
