package com.ot.dto.request;

import lombok.Data;

@Data
public class UpdateHospitalRequest {

    private String name;
    private String address;
    private String city;
    private Boolean isActive;
}