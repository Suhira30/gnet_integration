package com.gnet.integration.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class
GnetAccount {
private String accountNumber;
private String accountName;
private String callerName;
private String callerNumber;
private String callerEmail;

}
