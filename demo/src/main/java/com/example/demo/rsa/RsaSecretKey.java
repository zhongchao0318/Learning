package com.example.demo.rsa;

import lombok.Data;

import java.io.Serializable;

@Data
public class RsaSecretKey {

    private String privateKey;
    private String publicKey;

    public RsaSecretKey() {
    }

    public RsaSecretKey(String privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
}
