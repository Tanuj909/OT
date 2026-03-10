package com.ot.dto.response;

import lombok.Data;

@Data
public class AdminResponse {

    private Long id;
    private String name;
    private String email;
    private String role;
    private Boolean active;
    private HospitalSummaryResponse hospital;
}