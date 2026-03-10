package com.ot.dto.response;

import com.ot.enums.RoleType;
import lombok.Data;

@Data
public class StaffResponse {

    private Long id;
    private String name;
    private String email;
    private RoleType role;
    private Boolean active;
    private HospitalSummaryResponse hospital;
}