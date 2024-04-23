package com.serius.infobook.payload;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Data
public class ClientResponse {
    private String type;
    private String token;
    private long expiretime;
}
