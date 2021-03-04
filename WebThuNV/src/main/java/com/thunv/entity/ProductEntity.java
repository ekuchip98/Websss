package com.thunv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
@Data
public class ProductEntity extends BaseEntity{

    @Column(name = "code")
    @NotNull(message = "Code not null")
    private String code;

    @Column(name = "name")
    @NotNull(message = "Name not null")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "title")
    private String title;

    @Column(name = "sale", length = 4)
    @Min(value = 0, message = "greater than or equal to 0")
    @Max(value = 100, message = "less than or equal to 100")
    private Integer sale;

    @Column(name = "description", columnDefinition = "TEXT", length = 2000)
    private String description;

    @OneToOne
    @JoinColumn(name = "detail_id")
    private ProductDetailEntity productDetail;

    @ManyToMany(mappedBy = "products")
    private List<CategoryEntity> categories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "trademark_id")
    private TrademarkEntity trademark;

}
