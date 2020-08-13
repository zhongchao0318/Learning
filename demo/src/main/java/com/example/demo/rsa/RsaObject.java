package com.example.demo.rsa;

import lombok.Data;

import java.io.Serializable;

@Data
public class RsaObject {

    private String key;
    private String keypub;

    public RsaObject() {
    }

    public RsaObject(String key, String keypub) {
        this.key = key;
        this.keypub = keypub;
    }
}
