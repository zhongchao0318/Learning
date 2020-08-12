package com.example.demo.dto;

import lombok.Data;

import java.util.List;

/**
 * 目录简要
 */
@Data
public class MenuSimpleDto {
    private String id;
    private String name;
    private String parentNode;
    private List<MenuSimpleDto> children;
}
