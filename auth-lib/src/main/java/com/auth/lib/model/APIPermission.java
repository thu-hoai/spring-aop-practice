package com.auth.lib.model;

import lombok.Data;

import java.math.BigInteger;

@Data
public class APIPermission {
    private BigInteger id;
    private String serviceId;
    private String clientId;
    private String apiPattern;
}
