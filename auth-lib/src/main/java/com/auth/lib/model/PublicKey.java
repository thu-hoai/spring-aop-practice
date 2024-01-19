package com.auth.lib.model;

import lombok.Data;

@Data
public class PublicKey {
    private String alg;
    private String value;
}
