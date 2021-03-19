package com.example.demo.test.ecology.params;

/**
 * 字段参数
 *
 * @Author: zc
 * @Date: 2021/3/1 14:03
 */
public class FieldString {
    private String fieldName;
    private String fieldValue;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String toString() {
        return "FieldString{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                '}';
    }
}


