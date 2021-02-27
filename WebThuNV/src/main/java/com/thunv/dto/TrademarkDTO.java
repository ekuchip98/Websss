package com.thunv.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TrademarkDTO extends BaseDTO{

    private String name;

    private String code;

    private String logo;

    private String title;
}
