package com.example.demo.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ChexkParam {
    public  static String voChexk(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors  = bindingResult.getFieldErrors();
            StringBuilder errMsg = new StringBuilder();
            for (FieldError fieldError: errors) {
                errMsg.append(fieldError.getDefaultMessage()).append(" ");
            }
            return errMsg.toString();
        }
        return null;
    }
}
