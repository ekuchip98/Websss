package com.thunv.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDetailDTO extends BaseDTO{

    private String size;
    private BigDecimal price;
    private BigDecimal priceOld;
    private Integer evaluate;
    private Integer quantity;
    private Integer view;
    private Integer favorite;
    private String author;
    private Integer weight;
    private String manufacture;
    private Integer status;

}
