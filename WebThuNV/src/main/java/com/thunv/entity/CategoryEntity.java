package com.thunv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categories")
@Data
public class CategoryEntity extends BaseEntity{

    @Column(name = "name")
    @NotNull(message = "Name not null")
    private String name;

    @Column(name = "code")
    @NotNull(message = "Code not null")
    private String code;

    @Column(name = "status", length = 2)
    private Integer status;

    @ManyToMany
    @JoinTable(name = "category_product",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<ProductEntity> products = new ArrayList<>();
}
