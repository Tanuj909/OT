package com.ot.dto.response;

import lombok.Data;

@Data
public class HospitalResponse {

    private Long id;
    private String name;
    private String address;
    private String city;
    private Boolean active;
}