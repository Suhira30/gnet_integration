package com.gnet.integration.dto.request.bookingreq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DumpGnetVQRequest {
    private String api;
    private Object request;
    private String supplierId;

}
