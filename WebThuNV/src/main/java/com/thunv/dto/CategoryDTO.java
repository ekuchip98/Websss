package com.thunv.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDTO extends BaseDTO{

    private String name;

    private String code;

    private Integer status;


}
