package com.thunv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "trademarks")
@Data
public class TrademarkEntity extends BaseEntity{

    @Column(name = "name")
    @NotNull(message = "Name not null")
    private String name;

    @Column(name = "code")
    @NotNull(message = "Code not null")
    private String code;

    @Column(name = "logo")
    private String logo;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "trademark")
    @JsonIgnore
    private List<ProductEntity> products = new ArrayList<>();
}
