package com.ot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateHospitalRequest {

    @NotBlank
    private String name;

    private String address;
    private String city;
}