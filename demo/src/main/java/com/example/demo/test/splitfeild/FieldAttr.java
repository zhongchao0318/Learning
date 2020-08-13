package com.example.demo.test.splitfeild;


import lombok.Data;

import java.io.Serializable;

/**
 * field attribute
 */
@Data
public class FieldAttr implements Serializable {
    private String field;
    private String value;
    private boolean isshow;
}
