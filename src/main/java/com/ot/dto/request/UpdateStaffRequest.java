package com.ot.dto.request;

import com.ot.enums.RoleType;

import lombok.Data;

@Data
public class UpdateStaffRequest {

    private String name;
    private String email;
    private RoleType role;
}
