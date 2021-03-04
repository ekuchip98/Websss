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
@Data
@Table(name = "roles")
public class RoleEntity extends BaseEntity{

    @Column(name = "name")
    @NotNull(message = "Name not null")
    private String name;

    @Column(name = "code")
    @NotNull(message = "Code not null")
    private String code;

    @Column(name = "active", length = 4)
    private Integer active;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users = new ArrayList<>();
}
