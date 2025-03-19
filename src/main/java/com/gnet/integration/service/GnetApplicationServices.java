package com.gnet.integration.service;

import com.gnet.integration.dto.request.bookingreq.GnetViewQuotesRequest;
import com.gnet.integration.dto.request.bookingreq.response.GnetViewQuotesResponse;

public interface GnetApplicationServices {

    /**
     * gnetGetViewQuotes
     *
     * @param replicationEngineViewQuotesRequest
     * @param accessToken
     * @return
     */
    GnetViewQuotesResponse gnetGetViewQuotes(GnetViewQuotesRequest replicationEngineViewQuotesRequest, String accessToken);
}