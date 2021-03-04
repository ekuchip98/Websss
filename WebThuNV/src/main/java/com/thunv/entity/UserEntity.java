package com.thunv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity{

    @Column(name = "username", unique = true, length = 50)
    @NotNull(message = "Name not null")
    private String username;

    @Column(name = "image")
    private String image;

    @Column(name = "email")
    @NotNull(message = "Email not null")
    @Email(message = "Email invalidate")
    private String email;

    @Column(name = "password")
    @NotNull(message = "Password not null")
    @Size(min = 4)
    private String password;

    @Column(name = "active", length = 4)
    private Integer active;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<RoleEntity> roles = new ArrayList<>();

}
