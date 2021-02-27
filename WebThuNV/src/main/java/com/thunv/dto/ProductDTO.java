package com.thunv.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDTO extends BaseDTO{

    private String code;
    private String name;
    private String image;
    private String title;
    private Integer sale;
}
