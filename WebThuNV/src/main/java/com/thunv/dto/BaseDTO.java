package com.thunv.dto;

import lombok.Data;
import java.util.Date;

@Data
public class BaseDTO {

    private Long id;
    private Date createdDate;
    private Date modifiedDate;
    private String createdBy;
    private String modifiedBy;
}
