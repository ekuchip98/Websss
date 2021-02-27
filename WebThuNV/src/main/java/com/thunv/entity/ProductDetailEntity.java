package com.thunv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product_details")
@Data
public class ProductDetailEntity extends BaseEntity{

    @Column(name = "size", length = 20)
    private String size;

    @Column(name = "price", precision = 13, scale = 0)
    private BigDecimal price;

    @Column(name = "price_old", precision = 13, scale = 0)
    private BigDecimal priceOld;

    @Column(name = "evaluate")
    private Integer evaluate;

    @Column(name = "quantity")
    @Min(value = 0)
    private Integer quantity;

    @Column(name = "view")
    @Min(value = 0)
    private Integer view;

    @Column(name = "favorite")
    @Min(value = 0)
    private Integer favorite;

    @Column(name = "author")
    private String author;

    @Column(name = "weight", length = 11)
    private Integer weight;

    @Column(name = "manufacture", length = 11)
    private String manufacture;

    @Column(name = "status", length = 2)
    private Integer status;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private ProductEntity product;

}
