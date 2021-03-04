package com.thunv.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDTO extends BaseDTO{

    private String code;
    private String description;
    private String name;
    private String image;
    private String title;
    private Integer sale;
    private Long trademarkId;
    private List<Long> categoryIds;
    private ProductDetailDTO productDetail;
}
