package com.gnet.integration.dto.request.bookingreq;
import lombok.Data;
import lombok.NonNull;
import software.amazon.awssdk.annotations.NotNull;

@Data
public class LoginRequest {
    @NotNull
    private String uid;
    @NotNull
    private String password;
}
