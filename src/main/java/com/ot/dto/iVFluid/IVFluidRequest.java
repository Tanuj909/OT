package com.ot.dto.iVFluid;

import java.time.LocalDateTime;

import com.ot.enums.VolumeUnit;

import lombok.Data;

@Data
public class IVFluidRequest {
    private String fluidType;
    private Integer volume;
    private VolumeUnit unit;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}